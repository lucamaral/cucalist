(function() {
  App.Eventos.Controller = {
    iniciar: function(){
      App.Eventos.Controller.renderizarEventoList();
    },
    renderizarEventoList: function(searchTerm){
      var eventosPromise = App.Eventos.Service.list(searchTerm);
      eventosPromise.then(function(eventos){
        var eventosInput = {
          'eventos': eventos
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
        var participantesPromise = App.Eventos.Service.getParticipantes(id);
        participantesPromise.then(function(){
          var cucasPromise = App.Eventos.Service.getOpcoes(id);
          cucasPromise.then(function(){
            var date = new Date(eventoPromise.responseJSON.prazo);
            var dateString = moment(date).format('DD MM YYYY');
            var evento = {
              'descricao': eventoPromise.responseJSON.descricao ,
              'titulo': eventoPromise.responseJSON.titulo ,
              'prazo': dateString,
              'pessoas': participantesPromise.responseJSON,
              'id': id,
              'cucas': cucasPromise.responseJSON
            }
            console.info(cucasPromise.responseJSON);
            App.Eventos.View.renderizarEventoDetalhes(evento);
            App.Eventos.View.bindEditarEvento(App.Eventos.Controller.renderizarEventoEdit)
            App.Eventos.View.bindVoltarEvento(App.Eventos.Controller.renderizarEventoList);
          })
        })
      })
    },
    renderizarEventoEdit: function(id){
      var eventoPromise = App.Eventos.Service.getEvento(id);
      var participantesPromise = App.Eventos.Service.getParticipantes(id);
      var pessoasPromise = App.Eventos.Service.getPessoas();
      var cucasPromise = App.Eventos.Service.getAllCucas();
      var opcoesPromise = App.Eventos.Service.getOpcoes(id);
      $.when(eventoPromise, participantesPromise, pessoasPromise, cucasPromise, opcoesPromise).then(function(){
        var date = new Date(eventoPromise.responseJSON.prazo);
        var dateString = moment(date).format('YYYY-MM-DD');
        var evento = {
          'descricao': eventoPromise.responseJSON.descricao ,
          'titulo': eventoPromise.responseJSON.titulo ,
          'prazo': dateString,
          'cucas': opcoesPromise.responseJSON,
          'pessoas': participantesPromise.responseJSON,
          'id': id
        }
        App.Eventos.View.renderizarEventoEdit(evento, pessoasPromise.responseJSON, cucasPromise.responseJSON);
        App.Eventos.View.bindCancelarEventoEdit(App.Eventos.Controller.renderizarEventoDetalhes);
        App.Eventos.View.bindSalvarEvento(App.Eventos.Controller.updateEvento);
      })
    },
    renderizarEventoNovo: function(){
      var pessoasPromise = App.Eventos.Service.getPessoas();
      pessoasPromise.then(function(){
        var cucasPromise = App.Eventos.Service.getAllCucas();
        cucasPromise.then(function(){
          App.Eventos.View.renderizarEventoNovo(pessoasPromise.responseJSON, cucasPromise.responseJSON);
          App.Eventos.View.bindSalvarEvento(App.Eventos.Controller.salvarEvento);
          App.Eventos.View.bindCancelarEvento(App.Eventos.Controller.renderizarEventoList);
        });
      });
    },
    updateEvento: function(evento){
      var novoEventoPromise = App.Eventos.Service.update(evento);
      novoEventoPromise.then( function() {
        App.Eventos.Controller.renderizarEventoDetalhes(evento.id);
      }, function(novoEventoPromise) {
        App.showMessage.error($('#evento-error-display-section'), novoEventoPromise.responseJSON.message);
      });
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
