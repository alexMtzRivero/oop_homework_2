<html>
    <head>
        <title>Blockbuster</title>
        <link rel="stylesheet" href="/style.css">
    </head>
    <body>
        <header>Books Library</header>

        <#list movies as id, movie>
            <section>
                <p>id: ${id}</p>
                <p>Title: <a href="movies/${movie.title}">${movie.title}</a></p>
                <p>Runtime: ${movie.runtime}</p>
                <p>Year: ${movie.year}</p>
                <img src="${movie.poster}" alt="Italian Trulli">
                <a class="delete" href="#">Delete</a>
            </section>
        </#list>

        <footer>Java Programming - Harbour.Space University</footer>
    </body>
</html>