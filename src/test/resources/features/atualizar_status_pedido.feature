# language: pt

Funcionalidade: Atualizar status do produto

  Cenário: pedido em preparação passa para status pronto
    Dado que existe um pedido em preparação na esteira de produção
    Quando o sistema receber uma solicitação para atualizar o status do pedido
    Então deve retornar o status atualizado do pedido como pronto
