(function() {
    App.Cucas.Controller = {
        iniciar: function() {
            App.Cucas.Controller.renderizar();
            App.Cucas.View.bindNovaCuca(App.Cucas.Controller.novaCuca);
            App.Cucas.View.bindPesquisaCuca(App.Cucas.Controller.pesquisar);
        },
        renderizar: function(searchTerm) {
            var cucasPromise = App.Cucas.Service.list(searchTerm);
            cucasPromise.then(App.Cucas.Controller.renderizarCucas);
        },
        renderizarCucas: function(cucas) {
            App.Cucas.View.limparLista();
            var cucasInput = {
                "cucas": cucas
            };
            App.Cucas.View.renderizarItems(cucasInput);
            App.Cucas.View.bindEditarCuca(App.Cucas.Controller.atualizarCuca);
            App.Cucas.View.bindRemoverCuca(App.Cucas.Controller.removerCuca);
        },
        novaCuca: function(novaCuca) {
            var novaCucaPromise = App.Cucas.Service.nova(novaCuca);
            novaCucaPromise.then( function(novaCuca) {
              App.Cucas.Controller.renderizar();
            }, function(novaCuca) {
              console.info(novaCuca);
              App.showMessage.error($("#error-display-section"), novaCuca.responseJSON.message);
            });
        },
        pesquisar: function(searchTerm) {
            console.info(searchTerm);
            App.Cucas.Controller.renderizar(searchTerm);
        },
        removerCuca: function(id) {
            var cucaARemoverPromise = App.Cucas.Service.remover(id);
            cucaARemoverPromise.then(function() {
                App.Cucas.Controller.renderizar();
            })
        },

        atualizarCuca: function(status, cucaEditada) {
            if(status){
              console.info(cucaEditada);
              var cucaEditadaPromise = App.Cucas.Service.editar(cucaEditada);
              cucaEditadaPromise.then( function(cucaAtualizada) {
                App.Cucas.View.renderizarItem(cucaAtualizada);
                App.Cucas.View.bindEditarCuca(App.Cucas.Controller.atualizarCuca);
                App.Cucas.View.bindRemoverCuca(App.Cucas.Controller.removerCuca);
              }, function (response){
                App.showMessage.error($("#error-display-section"), response.responseJSON.message);
              });
            }else{
              App.Cucas.View.renderizarItem(cucaEditada);
              App.Cucas.View.bindEditarCuca(App.Cucas.Controller.atualizarCuca);
              App.Cucas.View.bindRemoverCuca(App.Cucas.Controller.removerCuca);
            };
        }

  };
})();
