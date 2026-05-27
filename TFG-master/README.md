# TFG - Juego Star Wars

Pequeño juego en Pygame. Aquí tienes instrucciones para clonar, crear el entorno virtual y ejecutar el juego en Windows y Unix.

## Requisitos
- Python 3.10+ instalado
- Git

## Clonar el repositorio
```bash
git clone https://github.com/danielzdr/DanielZuniga_PRO.git
cd DanielZuniga_PRO/TFG-master
```

## Crear y activar entorno virtual (Windows)
```powershell
python -m venv .venv
.\.venv\Scripts\Activate.ps1    # PowerShell
# o
.\.venv\Scripts\activate.bat    # cmd
```

## Crear y activar entorno virtual (Linux / macOS)
```bash
python3 -m venv .venv
source .venv/bin/activate
```

## Instalar dependencias
Si hay un `requirements.txt`:
```bash
python -m pip install -r requirements.txt
```
Si no, instala `pygame` manualmente:
```bash
python -m pip install pygame
```

También puedes usar el `pyproject.toml` si prefieres herramientas como Poetry.

## Ejecutar el juego
- Desde terminal (con entorno activado):
```bash
.\.venv\Scripts\python.exe inicio.py   # Windows
# o
.venv/bin/python inicio.py                # Unix
```
- Desde VS Code: abre la carpeta del proyecto y ejecuta la tarea `Ejecutar juego` (label: `Ejecutar juego`).

## Git
He añadido un `.gitignore` en la raíz que incluye ` .venv/` para que el entorno virtual no se suba al repo.

## Ranking y base de datos
El juego guarda las puntuaciones en `ranking.db` (archivo SQLite) en la raíz del proyecto cuando se guardan puntuaciones.
Si quieres ver el contenido de la base de datos localmente:
```bash
# instalar sqlite3 si no lo tienes; en muchas distribuciones ya viene
sqlite3 ranking.db "SELECT id, nombre, puntuacion, nivel, fecha FROM ranking ORDER BY puntuacion DESC, fecha ASC;"
```

## Nota sobre assets grandes
Si los archivos de `assets/` son grandes, considera usar Git LFS o subirlos como release en GitHub.

---
Si quieres, hago el commit y push de este `README.md` ahora.