document.addEventListener("DOMContentLoaded", function() {
    const ligaSelect = document.querySelector("#ligaSelect");
    const ligaLogo = document.querySelector("#ligaLogo");
    const teamsDiv = document.querySelector("#teams");

    // json para las imagenes de las diferentes ligas
    const ligas = [
    {nombre: "La Liga", apiName: "Spanish La Liga", escudo: "img/ligas/laliga.png"},
    {nombre: "Premier League", apiName: "English Premier League", escudo: "img/ligas/premier.png"},
    {nombre: "Serie A", apiName: "Italian Serie A", escudo: "img/ligas/seriea.png"},
    {nombre: "Bundesliga", apiName: "German Bundesliga", escudo: "img/ligas/bundesliga.png"},
    {nombre: "Ligue 1", apiName: "French Ligue 1", escudo: "img/ligas/ligue1.png"},
    {nombre: "Primeira Liga", apiName: "Portuguese Primeira Liga", escudo: "img/ligas/portugal.png"}
    ];

    //json para los escudos de cada equipo en su respectiva liga
    const escudosPorLiga = {
    "Spanish La Liga": {
        "Athletic Bilbao": "img/equipos/laliga/bilbao.png",
        "Atlético Madrid": "img/equipos/laliga/atletico.png",
        "FC Barcelona": "img/equipos/laliga/barcelona.png",
        "Celta de Vigo": "img/equipos/laliga/celta.png",
        "Alavés": "img/equipos/laliga/alaves.png",
        "Elche": "img/equipos/laliga/elche.png",
        "Espanyol": "img/equipos/laliga/espanyol.png",
        "Getafe": "img/equipos/laliga/getafe.png",
        "Girona": "img/equipos/laliga/girona.png",
        "Levante": "img/equipos/laliga/levante.png"
    },
    "English Premier League": {
        "Arsenal": "img/equipos/premier/arsenal.png",
        "Aston Villa": "img/equipos/premier/aston_villa.png",
        "Bournemouth": "img/equipos/premier/bournemouth.png",
        "Brentford": "img/equipos/premier/brentford.png",
        "Brighton": "img/equipos/premier/brighton.png",
        "Burnley": "img/equipos/premier/burnley.png",
        "Chelsea": "img/equipos/premier/chelsea.png",
        "Crystal Palace": "img/equipos/premier/crystal_palace.png",
        "Everton": "img/equipos/premier/everton.png",
        "Fulham": "img/equipos/premier/fulham.png"
    },
    "Italian Serie A": {
        "AC Milan": "img/equipos/seriea/milan.png",
        "Atalanta": "img/equipos/seriea/atalanta.png",
        "Bologna": "img/equipos/seriea/bologna.png",
        "Cagliari": "img/equipos/seriea/cagliari.png",
        "Como": "img/equipos/seriea/como.png",
        "Cremonese": "img/equipos/seriea/cremonese.png",
        "Fiorentina": "img/equipos/seriea/fiorentina.png",
        "Genoa": "img/equipos/seriea/genoa.png",
        "Hellas Verona": "img/equipos/seriea/verona.png",
        "Inter Milan": "img/equipos/seriea/inter.png"
    },

    "German Bundesliga": {
        "Bayer Leverkusen": "img/equipos/bundesliga/leverkusen.png",
        "Bayern Munich": "img/equipos/bundesliga/bayern.png",
        "Borussia Dortmund": "img/equipos/bundesliga/dortmund.png",
        "Borussia Mönchengladbach": "img/equipos/bundesliga/monchengladbach.png",
        "Eintracht Frankfurt": "img/equipos/bundesliga/frankfurt.png",
        "Augsburg": "img/equipos/bundesliga/augsburg.png",
        "Heidenheim": "img/equipos/bundesliga/heidenheim.png",
        "Koln": "img/equipos/bundesliga/koln.png",
        "Freiburg": "img/equipos/bundesliga/freiburg.png",
        "Hamburgo": "img/equipos/bundesliga/hamburgo.png"
    },
    "French Ligue 1": {
        "Angers": "img/equipos/ligue1/angers.png",
        "Auxerre": "img/equipos/ligue1/auxerre.png",
        "Brest": "img/equipos/ligue1/brest.png",
        "Le Havre": "img/equipos/ligue1/lehavre.png",
        "Lens": "img/equipos/ligue1/lens.png",
        "Lorient": "img/equipos/ligue1/lorient.png",
        "Lyon": "img/equipos/ligue1/lyon.png",
        "Marseille": "img/equipos/ligue1/marseille.png",
        "Metz": "img/equipos/ligue1/metz.png",
    },
    "Portuguese Primeira Liga": {
        "Alverca": "img/equipos/portugal/alverca.png",
        "Aroca": "img/equipos/portugal/arouca.png",
        "Avs": "img/equipos/portugal/aves.png",
        "Benfica": "img/equipos/portugal/benfica.png",
        "Braga": "img/equipos/portugal/braga.png",
        "Casa Pia": "img/equipos/portugal/casapia.png",
        "Estoril": "img/equipos/portugal/estoril.png",
        "Estrela Amadora": "img/equipos/portugal/estrelaamadora.png",
        "Famalicao": "img/equipos/portugal/famalicao.png",
        "Porto": "img/equipos/portugal/porto.png"

    }
};

    // Función para obtener el escudo del equipo con lógica de coincidencia parcial
    function obtenerEscudoEquipo(nombreEquipo, ligaApiName) {
    if (escudosPorLiga[ligaApiName] && escudosPorLiga[ligaApiName][nombreEquipo]) {
        return escudosPorLiga[ligaApiName][nombreEquipo];
    }
    
    if (escudosPorLiga[ligaApiName]) {
        const equipoKeys = Object.keys(escudosPorLiga[ligaApiName]);
        
        for (const key of equipoKeys) {
            if (nombreEquipo.toLowerCase().includes(key.toLowerCase()) || 
                key.toLowerCase().includes(nombreEquipo.toLowerCase())) {
                return escudosPorLiga[ligaApiName][key];
            }
        }
    }
    
    return "img/escudo-default.png";
}

    // Crear opción por defecto
    const defaultOption = document.createElement("option");
    defaultOption.value = "";
    defaultOption.textContent = "Seleccione una liga";
    defaultOption.disabled = true;
    defaultOption.selected = true;
    ligaSelect.appendChild(defaultOption);

    // select
    ligas.forEach(liga => {
        const option = document.createElement("option");
        option.value = liga.apiName;
        option.textContent = liga.nombre;
        option.setAttribute("data-logo", liga.escudo);
        ligaSelect.appendChild(option);
    });

    // EventListener liga
    ligaSelect.addEventListener("change", function() {
        teamsDiv.innerHTML = "";
        const selected = ligaSelect.options[ligaSelect.selectedIndex];
        const logo = selected.getAttribute("data-logo");
        if(logo) {
            ligaLogo.src = logo;
            ligaLogo.classList.remove("d-none");
        } else {
            ligaLogo.classList.add("d-none");
        }

        const ligaApiName = ligaSelect.value;
        if(ligaApiName) cargarEquipos(ligaApiName);
    });

    
    function cargarEquipos(ligaApiName) {
        fetch(`https://www.thesportsdb.com/api/v1/json/123/search_all_teams.php?l=${encodeURIComponent(ligaApiName)}`)
        .then(res => res.json())
        .then(data => {
            if(!data.teams) {
                teamsDiv.innerHTML = "<p class='text-center'>No se encontraron equipos para esta liga.</p>";
                return;
            }

            data.teams.forEach(team => {
                const escudoEquipo = obtenerEscudoEquipo(team.strTeam, ligaApiName);
                const card = document.createElement("div");
                card.className = "col";

                card.innerHTML = `
    <div class="card h-100">
        <img src="${escudoEquipo}" 
             class="card-img-top team-logo" 
             alt="${team.strTeam}"
             onerror="this.onerror=null; this.src='img/escudo-default.png';">
        <div class="card-body d-flex flex-column">
            <h5 class="card-title">${team.strTeam}</h5>
            <button class="btn btn-primary mt-auto" onclick="cargarJugadores(${team.idTeam}, this)">Ver plantilla</button>
            <ul class="list-group list-group-flush mt-3 d-none" id="players-${team.idTeam}"></ul>
        </div>
    </div>`;                
    teamsDiv.appendChild(card);
            });
        })
        .catch(err => {
            console.log(err);
            teamsDiv.innerHTML = "<p class='text-center'>Error al cargar equipos</p>";
        });
    }

    
    window.cargarJugadores = function(idEquipo, btn) {
        const lista = document.querySelector(`#players-${idEquipo}`);

        if(!lista.classList.contains("d-none")) {
            lista.classList.add("d-none");
            btn.textContent = "Ver plantilla";
            return;
        }

        // Ocultar otras plantillas cuando selecionas una
        document.querySelectorAll("ul[id^='players-']").forEach(ul => ul.classList.add("d-none"));
        document.querySelectorAll("button").forEach(b => { if(b !== btn) b.textContent = "Ver plantilla"; });

        lista.innerHTML = "<li class='list-group-item'>Cargando jugadores...</li>";
        lista.classList.remove("d-none");
        btn.textContent = "Ocultar plantilla";

        fetch(`https://www.thesportsdb.com/api/v1/json/123/lookup_all_players.php?id=${idEquipo}`)
        .then(res => res.json())
        .then(data => {
            lista.innerHTML = "";
            if(!data.player) {
                lista.innerHTML = "<li class='list-group-item'>No hay jugadores disponibles</li>";
                return;
            }

            // card limitado a 20 jugadores
            const jugadores = data.player.slice(0, 20);
            jugadores.forEach(jugador => {
                lista.innerHTML += `
                    <li class="list-group-item d-flex align-items-center">
                        ${jugador.strCutout ? `<img src="${jugador.strCutout}" class="player-img">` : ""}
                        <span>${jugador.strPlayer} - Posición: ${jugador.strPosition ? jugador.strPosition : "N/A"} - Dorsal: ${jugador.strNumber ? jugador.strNumber : "N/A"}</span>
                    </li>
                `;
            });
        })
        .catch(() => {
            lista.innerHTML = "<li class='list-group-item'>Error al cargar jugadores</li>";
        });
    }
});
