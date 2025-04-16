function adicionarTarefa() {
  const tarefa = {
    nome: $('#nomeTarefa').val(),
    descricao: $('#descricaoTarefa').val()
  };

  $.ajax({
    url: '/tarefas/',
    type: 'POST',
    contentType: 'application/json',
    data: JSON.stringify(tarefa),
    success: function () {
      $('#formTarefa')[0].reset(); // limpa o formulário
      carregarTarefas(); // atualiza a lista automaticamente
    }
  });
}

function carregarTarefas() {
  $.ajax({
    url: '/tarefas/',
    type: 'GET',
    success: function (tarefas) {
      let lista = '';
      // novo código para pesquisa
      const pesquisa = $('#pesquisaTarefa').val()?.toLowerCase() || '';

      const tarefasFiltradas = tarefas.filter(tarefa =>
        tarefa.nome.toLowerCase().includes(pesquisa)
      );
      // fim do novo código
      //  tarefas.forEach foi alterado para tarefasFiltradas.forEach
      tarefasFiltradas.forEach(tarefa => {
        lista += `
            <li class="list-group-item d-flex justify-content-between align-items-start">
              <div>
                <strong id="nome-${tarefa.id}">${tarefa.nome}</strong><br>
                <small id="descricao-${tarefa.id}">${tarefa.descricao}</small>
              </div>
              <div>
                <button class="btn btn-sm btn-warning me-2" onclick="prepararEdicao(${tarefa.id})">Editar</button>
                <button class="btn btn-sm btn-danger" onclick="excluirTarefa(${tarefa.id})">Excluir</button>
              </div>
            </li>`;

      });

      $('#listaTarefas').html(lista);
    }
  });
}

function excluirTarefa(id) {
  if (confirm("Tem certeza que deseja excluir esta tarefa?")) {
    $.ajax({
      url: `/tarefas/${id}`,
      type: 'DELETE',
      success: function () {
        carregarTarefas();
      }
    });
  }
}

let idEditando = null;

function prepararEdicao(id) {
  const nome = $(`#nome-${id}`).text();
  const descricao = $(`#descricao-${id}`).text();

  $('#nomeEdicao').val(nome);
  $('#descricaoEdicao').val(descricao);
  idEditando = id;

  const modal = new bootstrap.Modal(document.getElementById('modalEdicao'));
  modal.show();
}

function atualizarTarefa() {
  const tarefaAtualizada = {
    nome: $('#nomeEdicao').val(),
    descricao: $('#descricaoEdicao').val()
  };

  $.ajax({
    url: `/tarefas/${idEditando}`,
    type: 'PUT',
    contentType: 'application/json',
    data: JSON.stringify(tarefaAtualizada),
    success: function () {
      carregarTarefas();
      idEditando = null;

      // Fechar o modal após a edição
      const modal = bootstrap.Modal.getInstance(document.getElementById('modalEdicao'));
      modal.hide();
    }
  });
}


// Carrega a lista ao iniciar a página
$(document).ready(function () {
  carregarTarefas();
});