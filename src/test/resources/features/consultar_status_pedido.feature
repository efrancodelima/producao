# language: pt

Funcionalidade: Fazer checkout

  Cenário: Cliente faz checkout do pedido
    Dado que o cliente possui um pedido
    Quando o pedido for enviado para checkout
    Então o sistema deve devolver o status do pedido como recebido
