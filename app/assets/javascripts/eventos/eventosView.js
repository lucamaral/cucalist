(function(){
  App.Eventos.View = {
    renderizarEventoList: function(eventosInput) {
      $('#page-wrapper').empty();
      var rendered = Mustache.render(App.Eventos.Templates.EventoListTemplate, eventosInput);
      $('#page-wrapper').append(rendered);
    },
    renderizarEventoNovo: function(pessoas) {
      $('#page-wrapper').empty();
      var rendered = Mustache.render(App.Eventos.Templates.EventoNovoTemplate);
      $('#page-wrapper').append(rendered);
      $('#select-pessoas').selectize({
        options: pessoas ,
        valueField: 'id' ,
        labelField: 'nome'
      });
    },
    renderizarEventoEdit: function(evento, pessoas) {
      $('#page-wrapper').empty();
      var rendered = Mustache.render(App.Eventos.Templates.EventoEditarTemplate, evento);
      $('#page-wrapper').append(rendered);
      var idInput = _.map(evento.pessoas, function(num, key) {
        return evento.pessoas[key].id;
      });
      var $select = $('#select-pessoas').selectize({
        options: pessoas ,
        items: idInput ,
        valueField: 'id' ,
        labelField: 'nome'
      });
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
        var id = $('.eventos-base').data('id');
        var titulo = $('#titulo-evento-input').val();
        var descricao = $('#descricao-evento-input').val();
        var date = $('#date-evento-input').val();
        var participantes = $('#select-pessoas').val();
        var eventoNovo = {
          'id': id,
          'titulo': titulo,
          'descricao': descricao,
          'prazo': date,
          'participantes': participantes
        }
        novoEventoCallback(eventoNovo);
      });
    },
    bindCancelarEvento: function(callback){
      $("#btn-cancel-new-evento").off();
      $('#btn-cancel-new-evento').click(function(event) {
        callback();
      });
    },
    bindCancelarEventoEdit: function(callback){
      $("#btn-cancel-new-evento").off();
      $('#btn-cancel-new-evento').click(function(event) {
        var id = $('.eventos-base').data('id');
        callback(id);
      });
    },
    bindVoltarEvento: function(callback){
      $("#btn-voltar-evento").off();
      $('#btn-voltar-evento').click(function(event) {
        callback();
      });
    },
    bindEditarEvento: function(callback){
      $("#btn-editar-evento").off();
      $('#btn-editar-evento').click(function(event) {
        var id = $('.eventos-base').data('id');
        callback(id);
      });
    }


  }
})()
