package es.upm.sos.practica1.service;




import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import es.upm.sos.practica1.exception.LibroNoEncontradoException;
import es.upm.sos.practica1.model.Libro;
import es.upm.sos.practica1.repository.LibroRepository;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LibroService {

    private final LibroRepository libroRepository;

    public Libro crearLibro(Libro libro) {
        return libroRepository.save(libro);
    }

    public void eliminarLibro(String isbn) {
        libroRepository.deleteById(isbn);
    }

    public Libro obtenerLibroPorIsbn(String isbn) {
        return libroRepository.findById(isbn)
                .orElseThrow(() -> new LibroNoEncontradoException(isbn));
    }


    @Transactional
    public Libro actualizarLibro(String isbn, Libro libroActualizado) {
        Libro libro = obtenerLibroPorIsbn(isbn);
        libro.setTitulo(libroActualizado.getTitulo());
        libro.setAutores(libroActualizado.getAutores());
        libro.setEdicion(libroActualizado.getEdicion());
        libro.setEditorial(libroActualizado.getEditorial());
        libro.setVolumen(libroActualizado.getVolumen());
        libro.setDisponible(libroActualizado.isDisponible());
        return libroRepository.save(libro);
    }

    public Page<Libro> obtenerTodosLosLibros(int page, int size) {
        Pageable paginable = PageRequest.of(page, size);
        return libroRepository.findAll(paginable);
    }

    public Page<Libro> buscarLibrosPorTitulo(String titulo, int page, int size) {
        Pageable paginable = PageRequest.of(page, size);
        return libroRepository.findByTituloContaining(titulo, paginable);
    }

    public Page<Libro> buscarLibrosDisponibles(boolean disponible, int page, int size) {
        Pageable paginable = PageRequest.of(page, size);
        disponible = true;
        return libroRepository.findByDisponible(disponible, paginable);
    }

    public Page<Libro> buscarLibrosPorTituloYDisp(String titulo, boolean disponible, int page, int size) {
        Pageable paginable = PageRequest.of(page, size);
        disponible = true;
        return libroRepository.findByTituloContainingAndDisponible(titulo, disponible, paginable);
    }

}
