
package com.fereyesp.nutridiaria


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

import androidx.compose.material3.ExperimentalMaterial3Api

import com.fereyesp.nutridiaria.data.Minuta
import com.fereyesp.nutridiaria.ui.theme.NutriDiariaTheme

import com.fereyesp.nutridiaria.ui.screen.NutriDiarioApp


val minutas = arrayOf(

    Minuta(
        dia = "Lunes",
        titulo = "Avena trasnochada con frutos rojos y chía",
        ingredientes = "1/2 taza de avena, 1 cda de semillas de chía, 1/2 taza de leche o bebida vegetal, 1/2 taza de frutos rojos, 1 cdta de miel",
        pasos = "1. Mezclar la avena, la chía y la leche en un frasco. 2. Dejar reposar en el refrigerador toda la noche. 3. Servir en la mañana con los frutos rojos y la miel por encima.",
        recomendacionNutricional = "Aporta fibra soluble e insoluble que favorece la digestión y mantiene la saciedad por más tiempo."
    ),
    Minuta(
        dia = "Martes",
        titulo = "Ensalada de quinua con garbanzos y palta",
        ingredientes = "1 taza de quinua cocida, 1/2 taza de garbanzos cocidos, 1/2 palta en cubos, 1/2 tomate picado, jugo de 1 limón, sal y aceite de oliva",
        pasos = "1. En un bol, mezclar la quinua fría y los garbanzos. 2. Agregar el tomate y la palta. 3. Aliñar con el jugo de limón, un chorrito de aceite de oliva y sal al gusto.",
        recomendacionNutricional = "Excelente fuente de proteína vegetal de alto valor biológico y grasas saludables para el corazón."
    ),
    Minuta(
        dia = "Miércoles",
        titulo = "Filete de salmón al horno con verduras",
        ingredientes = "150g de filete de salmón, 1/2 zapallo italiano picado, 1/2 pimentón en tiras, 1 cdta de aceite de oliva, orégano, sal y pimienta",
        pasos = "1. Precalentar el horno a 180°C. 2. Colocar las verduras y el salmón en una bandeja para horno. 3. Condimentar con aceite de oliva, orégano, sal y pimienta. 4. Hornear durante 15-20 minutos.",
        recomendacionNutricional = "Alto contenido de ácidos grasos Omega-3, fundamentales para la salud cardiovascular y cerebral."
    ),
    Minuta(
        dia = "Jueves",
        titulo = "Salteado de pollo con brócoli y arroz integral",
        ingredientes = "150g de pechuga de pollo en cubos, 1 taza de árboles de brócoli, 1/2 taza de arroz integral cocido, 1 cda de salsa de soya baja en sodio",
        pasos = "1. Cocinar el pollo en un sartén antiadherente con un chorrito de agua o aceite. 2. Agregar el brócoli al vapor y saltear por 5 minutos. 3. Incorporar la salsa de soya y servir junto al arroz integral.",
        recomendacionNutricional = "Combinación magra y de bajo índice glucémico que proporciona energía sostenida para el día."
    ),
    Minuta(
        dia = "Viernes",
        titulo = "Tortilla de espinacas y claras de huevo",
        ingredientes = "3 claras de huevo, 1 huevo entero, 1 taza de espinacas frescas picadas, 1/4 de cebolla picada, sal y pimienta al gusto",
        pasos = "1. Sofreír la cebolla y la espinaca en un sartén hasta que reduzcan. 2. Batir las claras con el huevo entero, sal y pimienta. 3. Verter los huevos sobre las verduras y cocinar a fuego lento por ambos lados.",
        recomendacionNutricional = "Opción rica en proteínas de alta calidad y baja en calorías, ideal para la reparación muscular."
    ),

    Minuta(
        dia = "Sábado (Almuerzo)",
        titulo = "Lentejas guisadas con camote y acelga",
        ingredientes = "1 taza de lentejas cocidas, 1/2 camote en cubos, 1 taza de acelga picada, 1/4 cebolla en cubos, 1 diente de ajo, comino, sal y aceite de oliva",
        pasos = "1. Sofreír cebolla y ajo con un chorrito de aceite de oliva. 2. Agregar el camote, las lentejas y un poco de agua o caldo vegetal. 3. Cocinar a fuego medio hasta que el camote esté blando y agregar la acelga en los últimos 3 minutos.",
        recomendacionNutricional = "Plato rico en hierro no hemo, fibra y carbohidratos complejos que aseguran energía limpia."
    ),
    Minuta(
        dia = "Domingo (Almuerzo)",
        titulo = "Pavo a la plancha con puré de zapallo camote",
        ingredientes = "150g de bistec de pavo, 200g de zapallo camote, 1 cdta de mantequilla, nuez moscada, sal y pimienta",
        pasos = "1. Hervir el zapallo camote pelado hasta que esté suave y molerlo agregando mantequilla, sal y pizca de nuez moscada. 2. Cocinar el bistec de pavo a la plancha con sal y pimienta por 3-4 minutos por lado. 3. Servir el pavo acompañado del puré.",
        recomendacionNutricional = "Comida de fácil digestión, muy baja en grasas saturadas y rica en betacarotenos (vitamina A)."
    ),
    Minuta(
        dia = "Lunes (Almuerzo)",
        titulo = "Bowl de atún, choclo, zanahoria y arroz integral",
        ingredientes = "1 lata de atún al agua, 1/2 taza de arroz integral cocido, 1/3 taza de choclo, 1/2 zanahoria rallada, 1/4 de palta, jugo de limón",
        pasos = "1. Escurrir el atún. 2. En un recipiente profundo disponer una base de arroz integral. 3. Colocar el atún, el choclo, la zanahoria y la palta en secciones. 4. Aliñar con limón y sal.",
        recomendacionNutricional = "Aporta proteínas de absorción rápida y carbohidratos integrales ideales para continuar la jornada laboral o de estudio."
    ),
    Minuta(
        dia = "Martes (Almuerzo)",
        titulo = "Garbanzos salteados con pimentón y espinaca",
        ingredientes = "1 taza de garbanzos cocidos, 1/2 pimentón rojo en tiras, 1 taza de espinacas, 1/2 cdta de pimentón dulce (paprika), 1 cdta de aceite de oliva",
        pasos = "1. En un sartén caliente con aceite de oliva, saltear el pimentón rojo por 3 minutos. 2. Agregar los garbanzos, la paprika, sal y pimienta salteando durante 5 minutos. 3. Incorporar la espinaca al final hasta que se reduzca.",
        recomendacionNutricional = "Rico en antioxidantes como la vitamina C del pimentón, la cual mejora la absorción del hierro de los garbanzos."
    ),
    Minuta(
        dia = "Miércoles (Almuerzo)",
        titulo = "Pechuga de pollo al limón con papas al horno",
        ingredientes = "150g de pechuga de pollo, 1 papa mediana con piel en gajos, jugo de 1 limón, orégano, romero, sal y aceite de oliva",
        pasos = "1. Mezlcar los gajos de papa con aceite de oliva, romero y sal. Hornear a 200°C por 25 minutos. 2. Marinar el pollo con jugo de limón y orégano. 3. Cocinar el pollo a la plancha hasta dorar y servir con las papas horneadas.",
        recomendacionNutricional = "Fuente equilibrada de potasio y proteínas para el rendimiento muscular sin necesidad de frituras."
    ),

    Minuta(
        dia = "Jueves (Once/Cena)",
        titulo = "Tostadas integrales con palta y huevo mollet",
        ingredientes = "2 rebanadas de pan integral de grano entero, 1/2 palta molida, 1 huevo, semillas de sésamo, sal y pimienta",
        pasos = "1. Hervir el huevo en agua hirviendo durante 6 minutos para lograr una yema cremosa. 2. Tostar el pan integral y untar la palta molida con sal. 3. Pelar el huevo, cortarlo por la mitad, colocar sobre el pan y espolvorear sésamo.",
        recomendacionNutricional = "Una opción nocturna o de tarde muy nutritiva, rica en grasas insaturadas y proteína de alta calidad."
    ),
    Minuta(
        dia = "Viernes (Once/Cena)",
        titulo = "Crema de zapallo italiano y zanahoria con crutones integrales",
        ingredientes = "1 zapallo italiano, 1 zanahoria, 1/4 cebolla, 1 rebanada de pan integral en cubos, 1 cdta de aceite de oliva, sal y pimienta",
        pasos = "1. Tostar los cubos de pan al horno o sartén sin aceite hasta que queden crocantes. 2. Cocinar las verduras troceadas en agua hirviendo con sal por 15 minutos. 3. Licuar las verduras con un poco de su caldo y un chorrito de aceite de oliva. Servir con los crutones.",
        recomendacionNutricional = "Cena ligera de muy fácil digestión e hidratante, idónea para conciliar un descanso reparador."
    ),
    Minuta(
        dia = "Sábado (Once/Cena)",
        titulo = "Fajita integral de queso fresco, tomate y albahaca",
        ingredientes = "1 tortilla rápida integral, 50g de queso fresco en láminas, 1/2 tomate en rodajas, hojas de albahaca fresca, 1 cdta de aceite de oliva",
        pasos = "1. Dorar ligeramente la tortilla integral en una sartén. 2. Disponer las láminas de queso fresco, las rodajas de tomate y las hojas de albahaca. 3. Agregar un hilo de aceite de oliva, doblar a la mitad o enrollar y servir tibia.",
        recomendacionNutricional = "Aporta calcio y proteínas frescas de bajo aporte calórico, perfecto para mantener una cena liviana."
    ),
    Minuta(
        dia = "Domingo (Once/Cena)",
        titulo = "Yogur griego con manzana picada, nueces y canela",
        ingredientes = "150g de yogur griego natural sin azúcar, 1/2 manzana verde picada en cubos, 4 nueces picadas, 1/2 cdta de canela en polvo",
        pasos = "1. Servir el yogur griego en un bol. 2. Añadir la manzana verde picada y las nueces troceadas. 3. Espolvorear canela por encima y mezclar bien antes de consumir.",
        recomendacionNutricional = "Promueve la salud intestinal gracias a sus probióticos y ayuda al control de la glucosa nocturna gracias a la canela."
    ),
    Minuta(
        dia = "Lunes (Once/Cena)",
        titulo = "Omelette de champiñones y queso bajo en grasa",
        ingredientes = "2 huevos enteros, 1/2 taza de champiñones laminados, 30g de queso mantecoso bajo en grasa o queso chacra, sal, pimienta y aceite en spray",
        pasos = "1. Saltear los champiñones en una sartén con spray antiadherente hasta dorar. 2. Batir los huevos con sal y pimienta y verter sobre los champiñones. 3. Añadir el queso, doblar por la mitad cuando empiece a cuajar y cocinar hasta fundir.",
        recomendacionNutricional = "Cena baja en carbohidratos, saciante y rica en micronutrientes como el selenio de los champiñones."
    )
)

enum class Pantalla {
    LOGIN, REGISTRO, RECUPERAR, MINUTA
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NutriDiariaTheme {
                NutriDiarioApp()
            }

        }
    }
}



