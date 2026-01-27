export class usuario{
    hobbies?:string[];
    constructor(
        private nombre:string, 
        private apellido:string,
        private edad:number,
        ){
            this.hobbies = [];
        }
        //metodo para poder agregar hobbies
         agregarHobbie(){
            this.hobbies?.push();
         }

         getNombre():string{
            return this.nombre;
         }

         getApellido():string{
            return this.apellido;
         }

         getEdad():number{
            return this.edad;
         }

         setHobbie(hobbie:string){
            this.hobbies?.push(hobbie);
         }

}