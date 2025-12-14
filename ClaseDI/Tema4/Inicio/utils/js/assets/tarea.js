class tarea{
    nombre;
    fecha;
    prioridad;
    constructor(nombre,fecha,prioridad){
        this.nombre=nombre;
        this.fecha=fecha;
        this.prioridad=prioridad;
    }

    //metodos
    mostrarDatos(){
        console.log(`El nombre es: ${this.nombre}, 
                    la fecha es: ${this.fecha},  
                    y la prioridad es: ${this.prioridad}`);
    }
    //getter y setter

    get getNombre(){
        return this.nombre;
    }

    set ssetNombre(nombre){
        this.nombre=nombre;
    }
}

let tarea1=new tarea("Comprar comida",new Date(),"Alta");
tarea1.setNombre("Tarea de pablo")
tarea1.mostrarDatos();