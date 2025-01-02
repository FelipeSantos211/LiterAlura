# LiterAlura - Catálogo de Livros 📚  
Este projeto foi desenvolvido como parte do desafio **Alura + Oracle ONE** e tem como objetivo criar um sistema de catálogo de livros, onde é possível realizar interações via console com os usuários. O sistema permite buscar livros pela **API Gutendex**, inserir livros manualmente no banco de dados, e realizar algumas consultas como a quantidade de livros por idioma ou listar autores.

<p align="center">
  <img src="https://drive.google.com/uc?id=1MxrUhHbZjX2c5eG3XQNAZ_7Asl4Za-iP" width="200"/>
</p>

---

## 📋 Funcionalidades  
- **Buscar livro por título na API Gutendex**: Permite ao usuário procurar livros pela API.
- **Inserir livro no banco de dados**: O livro pode ser inserido manualmente ou pela pesquisa na API.
- **Listar todos os livros**: Exibe todos os livros armazenados no banco de dados.
- **Listar todos os autores**: Exibe todos os autores presentes no banco de dados.
- **Exibir quantidade de livros em um idioma específico**: Mostra o número de livros em um determinado idioma.

---

## 🛠️ Estrutura do Projeto  
O código está estruturado de forma modular, com diferentes pacotes e classes para organização do sistema. As principais classes incluem:

- **`Author`**: Entidade JPA que representa o autor no banco de dados.
- **`Book`**: Entidade JPA que representa o livro no banco de dados.
- **`AuthorDTO`**: Data Transfer Object (DTO) para representar dados do autor.
- **`BookDTO`**: Data Transfer Object (DTO) para representar dados do livro.
- **`AuthorService`**: Responsável por interagir com a camada de persistência para os autores.
- **`BookService`**: Responsável por interagir com a camada de persistência para os livros.
- **`GutendexService`**: Serviço para realizar buscas de livros pela API Gutendex.
- **`ConsoleInteraction`**: Responsável pela interação com o usuário via terminal.

---

## 🚀 Como Executar  
### Passos para rodar o projeto:

1. **Clone o repositório**:
   ```bash
   git clone <https://github.com/FelipeSantos211/literalura>
   cd literalura


2. **Compile e execute o projeto**:
   ```bash
   mvn spring-boot:run
   ```

3. **Interação via terminal**:
   Após iniciar o sistema, o usuário verá um menu interativo no console com as opções de:
   - Buscar livro por título.
   - Inserir livro no banco manualmente.
   - Listar todos os livros e autores.
   - Consultar a quantidade de livros por idioma.

---

## 🧑‍💻 Dependências  
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database** (Banco de dados em memória para armazenar livros e autores)
- **Gutendex API**: API pública utilizada para buscar livros por título.

---

## 📄 Exemplo de Uso  
### Exemplo de interação com o terminal:

```text
Opções:
1. Buscar livro por título na API Gutendex
2. Inserir livro no banco
3. Listar todos os livros (API e Banco)
4. Listar todos os autores do banco
5. Exibir quantidade de livros em um idioma
6. Sair
Escolha uma opção: 1
Digite o título do livro: Romeo and Juliet
• Título: Romeo and Juliet, Idioma: en, Autor: Shakespeare, William
• Título: The Tragedy of Romeo and Juliet, Idioma: en, Autor: Shakespeare, William
• Título: Shakespeare's Tragedy of Romeo and Juliet, Idioma: en, Autor: Shakespeare, William

Opções:
1. Buscar livro por título na API Gutendex
2. Inserir livro no banco
3. Listar todos os livros (API e Banco)
4. Listar todos os autores do banco
5. Exibir quantidade de livros em um idioma
6. Sair
```

---

## 🧪 Testes  
- O projeto contém funcionalidades que podem ser testadas diretamente através do terminal.
- Teste o salvamento de livros, listagem de livros e autores, e verificação de idiomas.
- Verifique se o número de livros é corretamente contado por idioma.

---

## 🛡️ Licença  
Este projeto é desenvolvido como parte do programa **Oracle Next Education** e está disponível para fins educacionais e de aprendizado.

---

## 👥 Autor  
- **Felipe Santos**  
Desenvolvido como parte do desafio **Alura + Oracle ONE**.

---

## 🌐 Links Úteis  
- [Gutendex API](https://www.gutenberg.org/robots.txt)  
- [Documentação Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/)
