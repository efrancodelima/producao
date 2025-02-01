package br.com.fiap.soat.service.provider;

import br.com.fiap.soat.entity.RegistroProducaoJpa;
import br.com.fiap.soat.entity.StatusPedido;
import br.com.fiap.soat.repository.RegistroProducaoRepository;
import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ListarPedidosProducaoService {

  private final RegistroProducaoRepository repository;

  @Autowired
  public ListarPedidosProducaoService(RegistroProducaoRepository repository) {
    this.repository = repository;
  }
  
  public List<RegistroProducaoJpa> execute() {

    var listaRegistros = repository.findAllWithLatestTimestamp();

    excluirItensFinalizados(listaRegistros);

    if (listaRegistros.size() > 1) {
      ordenarRegistros(listaRegistros);
    }

    return listaRegistros;
  }

  private void excluirItensFinalizados(List<RegistroProducaoJpa> registros) {

    for (int i = 0; i < registros.size(); i++) {
      if (registros.get(i).getStatus() == StatusPedido.FINALIZADO) {
        registros.remove(registros.get(i));
      }
    }
  }

  private static void ordenarRegistros(List<RegistroProducaoJpa> registros) {

    registros.sort(Comparator
        .comparing((RegistroProducaoJpa r) -> {
          switch (r.getStatus()) {
            case PRONTO: return 1;
            case EM_PREPARACAO: return 2;
            case RECEBIDO: return 3;
            default: return 4;
          }
        })
        .thenComparing(RegistroProducaoJpa::getTimestamp));
  }
}
