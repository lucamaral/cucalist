(function(){

  App.Eventos.Service = {
    list: function(searchTerm){
      var url= '/api/eventos'
      if(searchTerm != null){
        url += "?searchTerm=" + searchTerm;
      }
      return $.ajax({
          type: 'GET',
          url: url,
          dataType: 'json'
      });
    },
    novo: function(evento) {
      return $.ajax({
        type: 'POST',
        url: '/api/eventos',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(evento)
      });
    },
    update: function(evento) {
      return $.ajax({
        type: 'POST',
        url: '/api/eventos/update',
        dataType: 'json',
        contentType: 'application/json',
        data: JSON.stringify(evento)
      });
    },
    remove: function(id) {
      return $.ajax({
        type: 'DELETE',
        url: '/api/eventos/' + id
      });
    },
    getEvento: function(id) {
      return $.ajax({
        type: 'GET',
        url: '/api/eventos/' + id
      });
    },
    getPessoas: function(){
      return $.ajax({
        type: 'GET',
        url: '/api/pessoas'
      })
    },
    getParticipantes: function(id){
      return $.ajax({
        type: 'GET',
        url: '/api/participantes/' + id
      })
    },
    getAllCucas: function(){
      return $.ajax({
          type: 'GET',
          url: '/api/cucas',
          dataType: 'json'
      });
    },
    getOpcoes: function(id){
      return $.ajax({
        type: 'GET',
        url: '/api/cucas/' + id
      })
    }

  }

})()
