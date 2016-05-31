(function(id) {
  App.Evento.Controller = {
    iniciar: function(id){
      console.info("cheguei no controller iniciar");
      App.Evento.Controller.renderizar(id);
    },
    renderizar: function(id){
      console.info("cheguei no controller renderizar");
      var eventoPromise = App.Evento.Service.getEvento(id);
      eventoPromise.then(App.Evento.Controller.renderizarEvento);
    },
    renderizarEvento: function(evento){
      console.info("cheguei no controller renderizar evento");
      var eventoInput = {
        "evento": evento
      };
      App.Evento.View.renderizarItem(eventoInput);
    }
  }
})()
