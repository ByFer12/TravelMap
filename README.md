## TravelMap
Se ha desarrollado esta aplicacion de escritorio realizada en java en el curso de estructura de datos, que simula un maps muy basico
pero se manejan grafos, arboles B+, graphviz para la graficacion, etc.
La aplicacion funciona de la siguiente manera:
- al ejecutarse el jar la aplicacion aparece en blanco sin opciones mas que la de cargar el archivo .csv del mapa
- una vez cargado la aplicacion renderiza la imagen del Grafo usando graphviz
- Luego aparecen opciones de origen luego opciones de destino, y elige la opcion de transporte si es en bicicleta, caminando o en bus
- Luego de la a Buscar, el programa es capaz de buscar la ruta mas corta u optima, la ruta regular y la peor ruta
- luego puede elegir el siguiente nodo a avanzar y el programa recalcula las siguientes rutas posibles, esto lo hace con cada movimiento de nodos pero no puede regresar
- Una vez dado eso se almacenan las rutas en un arbol B+ y se grafica usando Graphviz
