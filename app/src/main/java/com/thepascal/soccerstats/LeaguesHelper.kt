package com.thepascal.soccerstats

class Leagues {

    companion object{
        internal const val LIGA = "liga"
        internal const val PREMIER_LEAGUE = "premier-league"
        internal const val BUNDESLIGA = "bundesliga"
        internal const val SERIE_A = "serie-a"

        internal const val LIGA_TITLE = "LA LIGA"
        internal const val PREMIER_LEAGUE_TITLE = "PREMIER LEAGUE"
        internal const val BUNDESLIGA_TITLE = "BUNDESLIGA"
        internal const val SERIE_A_TITLE = "SERIE A"

        internal const val NEXT_SEASON = "20-21"
        internal const val CURRENT_SEASON = "19-20"
        internal const val LAST_SEASON = "17-18"
        internal const val LAST_TWO_SEASON = "16-17"

        internal val SEASONS_LIST = listOf(NEXT_SEASON, CURRENT_SEASON,
                                            LAST_SEASON, LAST_TWO_SEASON)

        fun getCurrentYear(){

        }
    }
}

class TopTeams{

    companion object{

        /* LA LIGA */
        internal const val REAL_MADRID = "real_madrid"
        internal const val REAL_MADRID_ID = "fw3ok0fty95tz0ydspi2g5yzghm5exdj"

        internal const val BARCELONA = "barcellona"
        internal const val BARCELONA_ID = "fe6e0zt76gaejrbjuq662ayqvxln4bmh"

        internal const val ATLETICO_MADRID = "atl_madrid"
        internal const val ATLETICO_MADRID_ID = "uskk92ojo4eb2qlfaodhsfn1ptnquegi"

        internal val LA_LIGA_TOP_TEAMS_MAP = mapOf(REAL_MADRID to REAL_MADRID_ID,
                                            BARCELONA to BARCELONA_ID,
                                            ATLETICO_MADRID to ATLETICO_MADRID_ID)

        internal val LA_LIGA_TOP_TEAMS_LIST = listOf("Real Madrid", "Barcellona",
                                                "Atl. Madrid")

        /* PREMIER LEAGUE */
        internal const val ARSENAL = "arsenal"
        internal const val ARSENAL_ID = "zymx5xdh4knl5dwbcfv3kszge9d8brnw"

        internal const val CHELSEA = "chelsea"
        internal const val CHELSEA_ID = "blfamr89lxeyywtsraiqzq5p5zuz57i6"

        internal const val LIVERPOOL = "liverpool"
        internal const val LIVERPOOL_ID = "akppwaoizlxbsa66oupfkawevutbnjxp"

        internal const val MAN_CITY = "man_city"
        internal const val MAN_CITY_ID = "xy8xez8msmv4qucyabkhhk4rqbimnprx"

        internal const val MAN_UNITED = "man_united"
        internal const val MAN_UNITED_ID = "qtjxv9d71ntirsgpjbmeefda4gewdnd9"

        internal const val TOTTENHAM = "tottenham"
        internal const val TOTTENHAM_ID = "kdrhmjjufk8em0fqjtaltym29v4dwhhb"

        internal val PREMIER_LEAGUE_TOP_TEAMS_MAP = mapOf(
            ARSENAL to ARSENAL_ID, CHELSEA to CHELSEA_ID, LIVERPOOL to LIVERPOOL_ID,
            MAN_CITY to MAN_CITY_ID, MAN_UNITED to MAN_UNITED_ID, TOTTENHAM to TOTTENHAM_ID)

        internal val PREMIER_LEAGUE_TOP_TEAMS_LIST = listOf("Arsenal", "Chelsea",
            "Liverpool", "Man. City", "Man. United", "Tottenham")

        /* SERIE A */
        internal val INTER = "inter"
        internal val INTER_ID = "691f5aae875365927e918fe8cdf59de2"

        internal val JUVENTUS = "juventus"
        internal val JUVENTUS_ID = "a9ef824ba73b0a57e982df21467c3efc"

        internal val MILAN = "milan"
        internal val MILAN_ID = "a9ef824ba73b0a57e982df21467c3efc"

        internal val NAPOLI = "napoli"
        internal val NAPOLI_ID = "b7f5f1b1be693a8e1c5c3aa5aee2a05b"

        internal val SERIE_A_TOP_TEAMS_MAP = mapOf(
            INTER to INTER_ID, JUVENTUS to JUVENTUS_ID, MILAN to MILAN_ID,
            NAPOLI to NAPOLI_ID)

        internal val SERIE_A_TOP_TEAMS_LIST = listOf("Inter", "Juventus",
            "Milan", "Napoli")
    }
}