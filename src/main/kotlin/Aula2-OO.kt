package br.ifpb.pdm

fun main() {
    val repositorioAnimal = RepositorioAnimal()
    var opcao = 0
    while (opcao != 10) {
        menu()
        print("Digite a opção: ")
        opcao = readlnOrNull()?.toInt() ?: 0
        when (opcao) {
            0 -> {
                print("Digite o nome do animal:")
                var nomeDoAnimal = readlnOrNull()?: ""
                println("")
                print("Digite a idade do animal:")
                var idadeDoAnimal =  readlnOrNull()?.toInt() ?: 0
                println("")
                print("Digite a cor do animal:")
                var corDoAnimal = readlnOrNull()?: ""
                corDoAnimal.uppercase()
                println("")
                print("Digite o tipo do animal:")
                var tipoDoAnimal = readlnOrNull()?: ""

                when(tipoDoAnimal.lowercase()){

                    "cachorro" -> {
                        val cachorro = Cachorro(idadeDoAnimal, Color.valueOf(corDoAnimal))
                        cachorro.nome = nomeDoAnimal
                        repositorioAnimal.adicionar(cachorro)
                    }
                    "gato" -> {
                        val gato = Gato(idadeDoAnimal, Color.valueOf(corDoAnimal))
                        gato.nome = nomeDoAnimal
                        repositorioAnimal.adicionar(gato)
                    }
                    "passaro" -> {
                        val passaro = Passaro(idadeDoAnimal, Color.valueOf(corDoAnimal))
                        passaro.nome = nomeDoAnimal
                        repositorioAnimal.adicionar(passaro)
                    }
                    "humano" -> {
                        val humano = Humano(idadeDoAnimal, Color.valueOf(corDoAnimal))
                        humano.nome = nomeDoAnimal
                        repositorioAnimal.adicionar(humano)
                    }
                }

            }
            1 -> {
                val cachorro = Cachorro(10, Color.VERMELHO)
                cachorro.nome = "Rex"
                repositorioAnimal.adicionar(cachorro)
            }
            2 -> {
                val gato = Gato(5, Color.VERMELHO)
                gato.nome = "Felix"
                repositorioAnimal.adicionar(gato)
            }
            3 -> {
                val passaro = Passaro(2, Color.VERDE)
                passaro.nome = "Piu"
                repositorioAnimal.adicionar(passaro)
            }
            4 -> {
                repositorioAnimal.listar()
            }
            5 ->{
                println("Animais da Cor Vermelha")
                repositorioAnimal.animaisDaCorX(Color.VERMELHO)

            }
            6 -> {
                println("Animais com 10 anos")
                repositorioAnimal.animaisComXIdade(10)
            }
            7 -> {
                repositorioAnimal.animais.forEach(Animal::emitirSom)
                repositorioAnimal.animais.forEach { it.emitirSom()}
            }
            8 -> {
                print("Digite o nome do animal a ser removido:")
                var nomeDoAnimal = readlnOrNull()

                if (nomeDoAnimal != null) {
                    repositorioAnimal.remover(nomeDoAnimal)
                }

            }
            9 -> {
                print("Digite o nome do animal:")
                var nomeDoAnimal = readlnOrNull()

                if (nomeDoAnimal != null) {
                    repositorioAnimal.buscarPeloNome(nomeDoAnimal)
                }
            }
        }

    }
}
enum class Color(){
    VERMELHO, AMARELO, VERDE
}
abstract class Animal(var idade: Int, var cor: Color) {
    open var nome: String = ""
        get() = "Animal: $field"
        set(valor) {
            field = valor
        }

    abstract fun emitirSom()

    open fun idadeEmAnosHumanos() {
        println( "Idade em anos Humanos: ${idade * 7}")
    }
}

class Cachorro(idade: Int, cor: Color) : Animal(idade, cor) {
    override var nome: String = ""
        get() = field
        set(valor) {
            field = valor
        }
    override fun emitirSom() {
        println("Au au")
    }
}
class Gato(idade: Int, cor: Color) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Miau")
    }
}

class Passaro(idade: Int, cor: Color) : Animal(idade, cor) {
    override fun emitirSom() {
        println("Piu piu")
    }
}

class Humano(idade: Int, cor: Color) : Animal(idade, cor){
    override fun emitirSom() {
        println("Olá")
    }
    override fun idadeEmAnosHumanos() {
        println(idade)
    }
}

fun menu() {
    println("0 - Inserir Dados")
    println("1 - Cachorro")
    println("2 - Gato")
    println("3 - Pássaro")
    println("4 - Listar Animais")
    println("5 - Listar Animais Pela Cor")
    println("6 - Listar Animais Pela Idade")
    println("7 - Emitir som")
    println("8 - Remover Animal da Lista")
    println("9 - Buscar Pelo Nome")
    println("10 - Sair")
}

class RepositorioAnimal {
    val animais: MutableList<Animal> = mutableListOf()

    fun adicionar(animal: Animal) {
        animais.add(animal)
    }

    fun listar() {
        animais.forEach { println(it.nome) }
    }
    fun remover(nomeDoAnimal: String){
        var animal = buscarPeloNome(nomeDoAnimal)
        if (animal != null){
            animais.remove(animal)
        }
    }

    fun animaisDaCorX(cor: Color){
        for( animal in animais){
            if(animal.cor == cor){
                println(animal.nome)
            }
        }
    }

    fun animaisComXIdade(idade: Int){
        for( animal in animais){
            if(animal.idade == idade){
                println(animal.nome)
            }
        }
    }

    fun buscarPeloNome(nomeDoAnimal: String): Animal? {
        for(animal in animais){
            if(animal.nome == nomeDoAnimal){
                println("$nomeDoAnimal encontrado")
                return animal
            }
        }
        println("$nomeDoAnimal não encontrado")
        return null

    }

}

