package com.example.tienda.dataset

import android.util.Log
import com.example.tienda.model.Producto

class DataSet {

    companion object {
        var lista: ArrayList<Producto> = arrayListOf(
            Producto(
                1,
                "Armario Moderno",
                112,
                249.99,
                "Armario de madera de roble con 3 puertas y espejo",
                "Muebles",
                "https://tse1.mm.bing.net/th/id/OIP.yZIxGGxukBEb2AP0ta8mygHaEb?rs=1&pid=ImgDetMain&o=7&rm=3"
            ),
            Producto(
                2,
                "Puerta",
                212,
                189.50,
                "Puerta corredera de vidrio templado",
                "Muebles",
                "https://tse1.explicit.bing.net/th/id/OIP.goaQzgMsbOYBLt9EY3jVRwHaJh?rs=1&pid=ImgDetMain&o=7&rm=3"
            ),
            Producto(
                3,
                "Smartphone X10",
                312,
                699.99,
                "Teléfono inteligente con 128GB almacenamiento",
                "Tecnología",
                "https://images.unsplash.com/photo-1511707171634-5f897ff02aa9?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&h=300&q=80"
            ),
            Producto(
                4,
                "Laptop Pro",
                412,
                1199.99,
                "Laptop profesional 16GB RAM, 512GB SSD",
                "Tecnología",
                "https://images.unsplash.com/photo-1496181133206-80ce9b88a853?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&h=300&q=80"
            ),
            Producto(
                5,
                "Jeans Slim Fit",
                512,
                49.99,
                "Pantalones jeans ajustados color azul",
                "Ropa",
                "https://images.unsplash.com/photo-1542272604-787c3835535d?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&h=300&q=80"
            ),
            Producto(
                6,
                "Camiseta Algodón",
                612,
                24.99,
                "Camiseta 100% algodón varios colores",
                "Ropa",
                "https://th.bing.com/th/id/OIP.tWf0iqwKYspyNN8LDpdl6gHaHa?w=189&h=190&c=7&r=0&o=7&dpr=1.6&pid=1.7&rm=3"
            ),
            Producto(
                7,
                "Sofá 3 Plazas",
                712,
                599.99,
                "Sofá moderno gris 3 plazas",
                "Muebles",
                "https://th.bing.com/th/id/OIP.gc9yfFxlIWjY8F7EE3m3ggHaHa?w=148&h=180&c=7&r=0&o=7&dpr=1.6&pid=1.7&rm=3"
            ),
            Producto(
                8,
                "Tablet",
                812,
                329.99,
                "Tablet Android 10 pulgadas",
                "Tecnología",
                "https://miro.medium.com/max/1400/1*M8LzOMuXOCkBMWtcZrFvZA.jpeg"
            ),
            Producto(
                9,
                "Chaqueta Denim",
                912,
                79.99,
                "Chaqueta de mezclilla estilo vintage",
                "Ropa",
                "https://th.bing.com/th/id/OPEC.fFhgADX7OFS3Cw474C474?w=186&h=241&o=7&dpr=1.6&pid=1.7&rm=3"
            ),
            Producto(
                10,
                "Mesa Centro",
                1012,
                129.99,
                "Mesa de centro de cristal y metal",
                "Muebles",
                "https://th.bing.com/th/id/OIP.I-CZ5MmHPktAMah9YVUmZAHaFI?w=255&h=180&c=7&r=0&o=7&dpr=1.6&pid=1.7&rm=3"
            ),
            Producto(
                11,
                "Auriculares Bluetooth",
                1112,
                89.99,
                "Auriculares inalámbricos con cancelación de ruido",
                "Tecnología",
                "https://images.unsplash.com/photo-1505740420928-5e560c06d30e?ixlib=rb-4.0.3&auto=format&fit=crop&w=400&h=300&q=80"
            ),
            Producto(
                12,
                "Zapatos Deportivos",
                1212,
                69.99,
                "Zapatos para running color negro",
                "Ropa",
                "https://th.bing.com/th/id/OIP.dsbcgJ1hZYqlWSMk8tyOSwHaEV?w=274&h=180&c=7&r=0&o=7&dpr=1.6&pid=1.7&rm=3"
            )
        )

        var listaCarrito: ArrayList<Producto> = arrayListOf()

        fun addProducto(x: Producto) {
            listaCarrito.add(x)
            Log.v("carrito", "Producto añadido: ${x.nombre}. Total: ${listaCarrito.size}")
        }

        fun getListaFiltrada(categoria: String): ArrayList<Producto> {
            return if (categoria == "Todas") {
                ArrayList(lista)
            } else {
                lista.filter {
                    normalizarTexto(it.categoria) == normalizarTexto(categoria)
                } as ArrayList<Producto>
            }
        }

        private fun normalizarTexto(texto: String): String {
            return texto.lowercase()
                .replace("á", "a")
                .replace("é", "e")
                .replace("í", "i")
                .replace("ó", "o")
                .replace("ú", "u")
                .replace("ü", "u")
                .replace(" ", "")
                .trim()
        }
    }
}