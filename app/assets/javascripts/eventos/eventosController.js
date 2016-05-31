(function() {
  App.Eventos.Controller = {
    iniciar: function(){
      console.info("cheguei no iniciar");
      App.Eventos.Controller.renderizar();
    },
    renderizar: function(searchTerm){
      console.info("cheguei no renderizar");
      var eventosPromise = App.Eventos.Service.list(searchTerm);
      eventosPromise.then(App.Eventos.Controller.renderizarEventos);
    },
    renderizarEventos: function(eventos){
      console.info("cheguei no renderizarEventos");
      App.Eventos.View.limparListaEventos();
      var eventosInput = {
        "eventos": eventos
      };
    App.Eventos.View.renderizarItems(eventosInput);
    }
  }
})()
