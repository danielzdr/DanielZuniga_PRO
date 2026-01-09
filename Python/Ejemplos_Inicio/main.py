import os
import threading
import time
from flask import Flask, jsonify
 
app = Flask(__name__)
 
# Variable global para almacenar el estado del hilo
thread_output = []
 
# Función que se ejecuta en un hilo separado
def background_task():
    global thread_output
    for i in range(1, 6):
        thread_output.append(f'Hola, bienvenido al mundo... {i}')
        time.sleep(1)  # Pausa de 1 segundo
    thread_output.append('Fin del hilo')
 
@app.route('/')
def index():
    return '''
    <h1>Ejecutar Hilo</h1>
    <button onclick="startThread()">Iniciar Hilo</button>
    <h2>Salida del Hilo:</h2>
    <pre id="output"></pre>
    <script>
        function startThread() {
            fetch('/start-thread')
                .then(response => response.json())
                .then(data => {
                    if (data.started) {
                        document.getElementById('output').innerText = 'Hilo iniciado...';
                        checkThreadOutput();
                    }
                });
        }
 
        function checkThreadOutput() {
            fetch('/thread-output')
                .then(response => response.json())
                .then(data => {
                    document.getElementById('output').innerText = data.output.join('\\n');
                    if (!data.finished) {
                        setTimeout(checkThreadOutput, 1000);
                    }
                });
        }
    </script>
    '''
 
@app.route('/start-thread')
def start_thread():
    # Iniciar el hilo solo si no está ya en ejecución
    if not any(thread.name == 'BackgroundThread' for thread in threading.enumerate()):
        thread = threading.Thread(target=background_task, name='BackgroundThread')
        thread.start()
        return jsonify({'started': True})
    else:
        return jsonify({'started': False})
 
@app.route('/thread-output')
def get_thread_output():
    global thread_output
    finished = 'Fin del hilo' in thread_output
    return jsonify({'output': thread_output, 'finished': finished})
 
def main():
    app.run(port=int(os.environ.get('PORT', 5000)), debug=True)
 
if __name__ == "__main__":
    main()