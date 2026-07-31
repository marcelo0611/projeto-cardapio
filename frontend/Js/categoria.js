document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const selectTipo = document.getElementById('tipo-categoria');
    const inputNomeItem = document.getElementById('nome-item');
    const pageContainer = document.querySelector('.page');

    const tableContainer = document.createElement('div');
    tableContainer.id = 'container-categorias';
    pageContainer.appendChild(tableContainer);

    const rotulosCategorias = {
        'entrada': 'Entrada',
        'prato-principal': 'Prato Principal',
        'sobremesa': 'Sobremesa'
    };

    function renderizarTabela() {
        const categorias = JSON.parse(localStorage.getItem('categorias')) || [];

        if (categorias.length === 0) {
            tableContainer.innerHTML = `
                <p style="margin-top: 30px; color: #666; font-style: italic;">
                    Nenhum item categorizado no momento.
                </p>`;
            return;
        }

        let html = `
            <h2 style="margin-top: 35px; margin-bottom: 15px; color: var(--primary-color);">Itens Categorizados</h2>
            <table style="width: 100%; border-collapse: collapse; margin-top: 10px;">
                <thead>
                    <tr style="background-color: var(--primary-color); color: white; text-align: left;">
                        <th style="padding: 12px;">Item / Prato</th>
                        <th style="padding: 12px;">Categoria</th>
                        <th style="padding: 12px; text-align: center;">Ações</th>
                    </tr>
                </thead>
                <tbody>
        `;

        categorias.forEach((item, index) => {
            html += `
                <tr style="border-bottom: 1px solid #ddd;">
                    <td style="padding: 10px;">${item.nome}</td>
                    <td style="padding: 10px;">${rotulosCategorias[item.tipo] || item.tipo}</td>
                    <td style="padding: 10px; text-align: center;">
                        <button onclick="excluirCategoria(${index})" style="background-color: var(--accent-color); padding: 5px 10px; font-size: 0.85rem;">
                            Excluir
                        </button>
                    </td>
                </tr>
            `;
        });

        html += `</tbody></table>`;
        tableContainer.innerHTML = html;
    }

    window.excluirCategoria = function(index) {
        const categorias = JSON.parse(localStorage.getItem('categorias')) || [];
        categorias.splice(index, 1);
        localStorage.setItem('categorias', JSON.stringify(categorias));
        renderizarTabela();
    };

    form.addEventListener('submit', (e) => {
        e.preventDefault();

        const tipo = selectTipo.value;
        const nome = inputNomeItem.value.trim();

        if (!tipo || !nome) {
            alert('Preencha todos os campos corretamente.');
            return;
        }

        const novaCategoria = { tipo, nome };
        const categorias = JSON.parse(localStorage.getItem('categorias')) || [];
        
        categorias.push(novaCategoria);
        localStorage.setItem('categorias', JSON.stringify(categorias));

        alert('Categoria vinculada com sucesso!');
        form.reset();
        renderizarTabela();
    });

    renderizarTabela();
});