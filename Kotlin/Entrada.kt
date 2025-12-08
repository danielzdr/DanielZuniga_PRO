
    fun main() {
        var nombre: String="Pablo"
        var edad: Int = 30
        val DNI : String ="123456A"
        //println("Por favor introduce tu nombre")
        //nombre= readln()
        println()
        //edad= readln().toInt()
        //saludar(vecesParam = 1)
        //Hola soy pablo y tengo 10 años y mi dni es 123456A
       // println("Hola mi nombre es ${nombre} tengo ${edad} y mi dni es {$DNI}")
        //sumar(5,7)

        //sumFlecha?.invoke(1,55)
        //decidirWhen(2)
        repetirFor()
    }

    private fun saludar(vecesParam: Unit) {

    }

    fun saludar(nombreParam:String="Sin nombre", vecesParam: Int){
        print("Hola $nombreParam, acabas de realizar")
    }

    fun sumar(op1:Int, op2:Int):Int{
        return op1+op2
    }

    val sumFlecha={p1:Int,p2:Int -> println("El resultado de la suma es ${p1+p2}") }


    fun decidirWhen(valor:Int){
        when(valor) {
            1 -> {
                println("El caso tiene ccomo valor 1")
            }
            2->{
                println("El caso tiene como valor 2")
            }
            3->{
                println("El caso tiene como valor 3")
            }
            4->{
                println("El caso tiene como valor 4")
            }
        }

    }

    fun repetirFor(){
        /*for (i in 0..9 step 2){
            println(i)
        }*/
        //(Rangos).foreach (Int->Unit)
        //Si la funcion de flecha solo tiene un argumento-> este se puede llamar con la palabra reservada it
        (20..30).forEachIndexed { index, item ->
            println("Elemento en posicion $index con valor $item")
        }
    }
