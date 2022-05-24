package com.example.danp

data class District(val name: String, val code: Int)

data class BusLine(
    val name: String,
    val districtCode: Int,
    val color: String,
    val quantity: Int,
    val route: String,
    val imageId: Int = R.drawable.microbus
)

val districtsList = listOf(
    District(name = "Arequipa", code = 1),
    District(name = "Selva Alegre", code = 2),
    District(name = "Cayma", code = 3),
    District(name = "C. Colorado", code = 4),
    District(name = "Socabaya", code = 5),
    District(name = "Miraflores", code = 6),
    District(name = "Paucarpata", code = 7),
    District(name = "J.L.B. y Rivero", code = 8),
    District(name = "Mariano Melgar", code = 9),
    District(name = "Yanahuara", code = 10),
)

val busesList = listOf(
    BusLine(name = "AQP MASIVO", districtCode = 7, color = "Amarillo", quantity = 30, route = "A-7", imageId = R.drawable.masivo),
    BusLine(name = "SEGRAMPO", districtCode = 1, color = "Amarillo", quantity = 30, route = "A-7", imageId = R.drawable.segrampo),
    BusLine(name = "AQP MASIVO", districtCode = 8, color = "Amarillo", quantity = 30, route = "A-7", imageId = R.drawable.masivo),
    BusLine(name = "INTEGRA AREQUIPA", districtCode = 10, color = "Rojo", quantity = 30, route = "78", imageId = R.drawable.integra),
    BusLine(name = "INTEGRA AREQUIPA", districtCode = 1, color = "Rojo", quantity = 30, route = "78", imageId = R.drawable.integra),
    BusLine(name = "INTEGRA AREQUIPA", districtCode = 4, color = "Rojo", quantity = 30, route = "78", imageId = R.drawable.integra),
    BusLine(name = "TRANSCAYMA", districtCode = 3, color = "Verde", quantity = 30, route = "24", imageId = R.drawable.transcayma),
    BusLine(name = "TRANSCAYMA", districtCode = 10, color = "Verde", quantity = 30, route = "24", imageId = R.drawable.transcayma),
    BusLine(name = "TRANSCAYMA", districtCode = 1, color = "Verde", quantity = 30, route = "24", imageId = R.drawable.transcayma),
    BusLine(name = "TRANSCAYMA", districtCode = 8, color = "Verde", quantity = 30, route = "24", imageId = R.drawable.transcayma),
    BusLine(name = "EMARSISTRAN", districtCode = 8, color = "Azul", quantity = 30, route = "17"),
    BusLine(name = "EMARSISTRAN", districtCode = 5, color = "Azul", quantity = 30, route = "17"),
    BusLine(name = "EMARSISTRAN", districtCode = 7, color = "Azul", quantity = 30, route = "17"),
    BusLine(name = "UNION AQP", districtCode = 2, color = "Celeste", quantity = 30, route = "C-1", imageId = R.drawable.union),
    BusLine(name = "UNION AQP", districtCode = 6, color = "Celeste", quantity = 30, route = "C-1", imageId = R.drawable.union),
    BusLine(name = "UNION AQP", districtCode = 1, color = "Celeste", quantity = 30, route = "C-1", imageId = R.drawable.union),
    BusLine(name = "MEGABUS AQP", districtCode = 8, color = "Morado", quantity = 30, route = "C-10", imageId = R.drawable.megabus),
    BusLine(name = "MEGABUS AQP", districtCode = 1, color = "Morado", quantity = 30, route = "C-10", imageId = R.drawable.megabus),
    BusLine(name = "MEGABUS AQP", districtCode = 9, color = "Morado", quantity = 30, route = "C-10", imageId = R.drawable.megabus),
    BusLine(name = "GRAN PACHACUTEC", districtCode = 1, color = "Negro", quantity = 30, route = "C-2"),
    BusLine(name = "GRAN PACHACUTEC", districtCode = 2, color = "Negro", quantity = 30, route = "C-2"),
    BusLine(name = "GRAN PACHACUTEC", districtCode = 6, color = "Negro", quantity = 30, route = "C-2"),
    BusLine(name = "TRANSP. CONO NORTE", districtCode = 4, color = "Marron", quantity = 30, route = "C-7"),
    BusLine(name = "TRANSP. CONO NORTE", districtCode = 8, color = "Marron", quantity = 30, route = "C-7"),
    BusLine(name = "TRANSP. CONO NORTE", districtCode = 5, color = "Marron", quantity = 30, route = "C-7"),
    BusLine(name = "CHARACATO SABANDIA", districtCode = 8, color = "Gris", quantity = 30, route = "C-8"),
    BusLine(name = "CHARACATO SABANDIA", districtCode = 9, color = "Gris", quantity = 30, route = "C-8"),
    BusLine(name = "CHARACATO SABANDIA", districtCode = 5, color = "Gris", quantity = 30, route = "C-8"),
)
