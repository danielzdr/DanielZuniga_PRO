export class Tarea {
    prioridad?: string[];
    listaTareas: Tarea[] = [];
    constructor(
        private nombre: string,
        private fecha: Date,
        private nombreResponsable: string,
        private items: string[],
        private completada: boolean = false
    ){
        this.prioridad = [];
        
    }
    agregarPrioridad(nivel: string): void {
        this.prioridad?.push(nivel);
    }

    getNombre(): string {
        return this.nombre;
    }
    getFecha(): Date {
        return this.fecha;
    }
    getNombreResponsable(): string {
        return this.nombreResponsable;
    }
    getItems(): string[] {
        return this.items;
    }
    isCompletada(): boolean {
        return this.completada;
    }
     agregarTarea(tarea: Tarea): void {
    this.listaTareas.push(tarea);
  }

  obtenerTareas(): Tarea[] {
    return [...this.listaTareas]; // Devuelve copia del array
  }

  eliminarTarea(index: number): void {
    if (index >= 0 && index < this.listaTareas.length) {
      this.listaTareas.splice(index, 1);
    }
  }

  limpiarTareas(): void {
    this.listaTareas = [];
  }
}