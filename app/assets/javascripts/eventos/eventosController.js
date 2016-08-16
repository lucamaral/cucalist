(function() {
  App.Eventos.Controller = {
    iniciar: function(){
      App.Eventos.Controller.renderizarEventoList();
    },
    renderizarEventoList: function(searchTerm){
      var eventosPromise = App.Eventos.Service.list(searchTerm);
      eventosPromise.then(function(eventos){
        var eventosInput = {
          "eventos": eventos
        };
        App.Eventos.View.renderizarEventoList(eventosInput);
        App.Eventos.View.bindRemoverEvento(App.Eventos.Controller.removerEvento);
        App.Eventos.View.bindRenderEventoNovo(App.Eventos.Controller.renderizarEventoNovo);
        App.Eventos.View.bindRenderEventoDetalhes(App.Eventos.Controller.renderizarEventoDetalhes);
      });
    },
    renderizarEventoDetalhes: function(id){
      var eventoPromise = App.Eventos.Service.getEvento(id);
      eventoPromise.then(function(){
        var date = new Date(eventoPromise.responseJSON.prazo);
        var dateString = moment(date).format('DD MM YYYY');
        var evento = {
          'descricao': eventoPromise.responseJSON.descricao ,
          'titulo': eventoPromise.responseJSON.titulo ,
          'prazo': dateString
        }
        App.Eventos.View.renderizarEventoDetalhes(evento);
        App.Eventos.View.bindVoltarEvento(App.Eventos.Controller.renderizarEventoList);
      })
    },
    renderizarEventoNovo: function(){
      App.Eventos.View.renderizarEventoNovo();
      App.Eventos.View.bindSalvarEvento(App.Eventos.Controller.salvarEvento);
      App.Eventos.View.bindCancelarEvento(App.Eventos.Controller.renderizarEventoList);
    },
    salvarEvento: function(novoEvento){
      var novoEventoPromise = App.Eventos.Service.novo(novoEvento);
      novoEventoPromise.then( function() {
        App.Eventos.Controller.renderizarEventoList();
      }, function(novoEventoPromise) {
        App.showMessage.error($('#evento-error-display-section'), novoEventoPromise.responseJSON.message);
      });
    },
    removerEvento: function(id){
      var eventoARemoverPromise = App.Eventos.Service.remove(id);
      eventoARemoverPromise.then(function(){
        App.Eventos.Controller.renderizarEventoList();
      });
    }
  }
})()
;
