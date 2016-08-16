(function(){
  App.Eventos.View = {
    renderizarEventoList: function(eventosInput) {
      $('#page-wrapper').empty();
      var rendered = Mustache.render(App.Eventos.Templates.EventoListTemplate, eventosInput);
      $('#page-wrapper').append(rendered);
    },
    renderizarEventoNovo: function() {
      $('#page-wrapper').empty();
      var rendered = Mustache.render(App.Eventos.Templates.EventoNovoTemplate);
      $('#page-wrapper').append(rendered);
    },
    renderizarEventoDetalhes: function(evento) {
      $('#page-wrapper').empty();
      var rendered = Mustache.render(App.Eventos.Templates.EventoDetalhesTemplate, evento);
      $('#page-wrapper').append(rendered);
    },
    bindRenderEventoDetalhes: function(callback){
      $('.btn-evento-detalhes').off();
      $('.btn-evento-detalhes').click(function(event) {
        var id = $(event.target).closest('li').data("id");
        callback(id);
      });
    },
    bindRenderEventoNovo: function(callback) {
      $("#novo-evento-button").off();
      $("#novo-evento-button").click(function(event) {
        console.info('novo evento click');
        callback();
      });
    },
    bindRemoverEvento: function(removeCallback){
      $('.eventos-button-remover').off();
      $('.eventos-button-remover').click(function(event) {
        var id = $(event.target).closest('li').data("id");
        removeCallback(id);
      });
    },
    bindSalvarEvento: function(novoEventoCallback){
      $("#btn-save-new-evento").off();
      $('#btn-save-new-evento').click(function(event) {
        var titulo = $('#titulo-evento-input').val();
        var descricao = $('#descricao-evento-input').val();
        var date = $('#date-evento-input').val();
        var eventoNovo = {
          'titulo': titulo,
          'descricao': descricao,
          'prazo': date
        }
        novoEventoCallback(eventoNovo);
      });
    },
    bindCancelarEvento: function(){

    }


  }
})()
