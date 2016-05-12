(function() {

    App.Cucas.Service = {
        list: function(searchTerm) {
            var url = '/api/cucas';
            if(searchTerm != null){
              url += "?searchTerm=" + searchTerm;
            }
            return $.ajax({
                type: 'GET',
                url: url,
                dataType: 'json'
            });
        },
        nova: function(novaCuca) {
            return $.ajax({
                type: 'POST',
                url: '/api/cucas',
                dataType: 'json',
                contentType: 'application/json',
                data: JSON.stringify(novaCuca)
            });
        }
    };

})();
