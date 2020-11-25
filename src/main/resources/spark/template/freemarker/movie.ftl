<html>
    <head>
        <title>Movie ${movie.title}</title>
        <link rel="stylesheet" href="/style.css">
    </head>
    <body>
        <header>Movie  "${movie.title}"</header>
        <img src="${movie.poster}" alt="Italian Trulli">
        <section>
            <h3>director: ${movie.director.name}</h3>
            <p>${movie.plot}</p>
            <h3>Actors:</h3>
            <ul>
                <#list movie.actors as item>
                <li> ${item.name} as ${item.as} </li>
            </#list>
        </ul>
            <h3>Screened in :</h3>
            <ul>
                <#list movie.countries as item>
                <li> ${item}</li>
                </#list>
            </ul>

        <h3>Awards:</h3>
        <div>${movie.awards}</div>
        </section>

        <footer>Java Programming - Harbour.Space University</footer>
    </body>
</html>