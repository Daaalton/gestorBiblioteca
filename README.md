# gestorBiblioteca
# Implementación de una API REST y un prototipo funcional de un sencillo servicio de gestión de inventario para la biblioteca de una escuela. Donde los usuarios de esta aplicación puedan realizar distintas operaciones sobre la gestión de inventario (añadir, borrar libros) y préstamo de libros (préstamo o devoluciones de libros). Se asume que la autenticación y autorización de la herramienta está realizada y por tanto no hay que implementarla.
# En este servicio se podrán realizar acciones como añadir, borrar, editar libros del inventario, tomar prestados libros para un período de tiempo o realizar las devoluciones de los libros prestados. Para ello, se debe tener en cuenta que los libros deben estar representados por el título del libro, autor/es, edición, ISBN y editorial.
# El servicio debe implementar a través de la API las siguientes operaciones:
# • Añadir un usuario nuevo a la aplicación. Los datos básicos de un usuario son: nombre de usuario, matrícula, fecha de nacimiento y correo electrónico.
# • Ver los datos básicos del usuario.
# • Cambiar datos básicos de un perfil de usuario.
# • Borrar un perfil de usuario.
# • Obtener una lista de todos los usuarios en la aplicación.
# • Añadir libros a la biblioteca. Además, puede modificar y/o dar de baja libros concretos.
# • Los usuarios pueden listar los libros de la biblioteca. Esta lista debe permitir ser filtrada por patrón de título (eg. Buscar todos los libros que contengan “Rest” en su título, “Principios Restful”, “Rest recetas” …etc.).1
# • Se debe permitir filtrar este listado de libros para obtener únicamente los libros que estén disponibles.
# • Los usuarios pueden tomar prestados libros durante un período de dos semanas.
# • Los usuarios pueden devolver libros prestados. Si los devuelven fuera del plazo de préstamo, se aplicará una sanción y no podrán hacer prestamos en una semana.
# • Los usuarios pueden ampliar el préstamo de un libro, siempre que no se haya terminado el plazo de préstamo anterior.
# • Se puede obtener la lista de todos los préstamos actuales de un usuario. Además, ese listado debe permitir la opción de ser filtrada por fechas.
# • Se debe permitir también consultar el histórico de libros que ha tenido prestados un usuario.
# • Se debe facilitar consultar rápidamente toda la información de actividad relacionada con un usuario. Esta respuesta debe contener toda la información relativa al usuario, así como; la lista de libros que tiene actualmente prestados ordenada por fecha de préstamos, el historial de préstamos con los últimos 5 libros que ya ha devuelto.
