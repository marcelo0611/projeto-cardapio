document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const inputNome = document.getElementById('nome-fornecedor');
    const inputCnpj = document.getElementById('cnpj');
    const inputTelefone = document.getElementById('telefone');

    inputCnpj.addEventListener('input', (e) => {
        let value = e.target.value.replace(/\D/g, ''); 
        if (value.length > 14) value = value.slice(0, 14);

        value = value.replace(/^(\d{2})(\d)/, '$1.$2');
        value = value.replace(/^(\d{2})\.(\d{3})(\d)/, '$1.$2.$3');
        value = value.replace(/\.(\d{3})(\d)/, '.$1/$2');
        value = value.replace(/(\d{4})(\d)/, '$1-$2');

        e.target.value = value;
    });

    inputTelefone.addEventListener('input', (e) => {
        let value = e.target.value.replace(/\D/g, '');
        if (value.length > 11) value = value.slice(0, 11);

        if (value.length > 10) {
            value = value.replace(/^(\d{2})(\d{5})(\d{4})$/, '($1) $2-$3');
        } else if (value.length > 5) {
            value = value.replace(/^(\d{2})(\d{4})(\d{0,4})$/, '($1) $2-$3');
        } else if (value.length > 2) {
            value = value.replace(/^(\d{2})(\d{0,5})$/, '($1) $2');
        }

        e.target.value = value;
    });

    const pageContainer = document.querySelector('.page');
    const tableContainer = document.createElement('div');
    tableContainer.id = 'container-tabela';
    pageContainer.appendChild(tableContainer);

    function renderizarTabela() {
        const fornecedores = JSON.parse(localStorage.getItem('fornecedores')) || [];

        if (fornecedores.length === 0) {
            tableContainer.innerHTML = `
                <p style="margin-top: 30px; color: #666; font-style: italic;">
                    Nenhum fornecedor cadastrado até o momento.
                </p>`;
            return;
        }

        let html = `
            <h2 style="margin-top: 35px; margin-bottom: 15px; color: var(--primary-color);">Fornecedores Cadastrados</h2>
            <table style="width: 100%; border-collapse: collapse; margin-top: 10px;">
                <thead>
                    <tr style="background-color: var(--primary-color); color: white; text-align: left;">
                        <th style="padding: 12px;">Razão Social</th>
                        <th style="padding: 12px;">CNPJ</th>
                        <th style="padding: 12px;">Telefone</th>
                        <th style="padding: 12px; text-align: center;">Ações</th>
                    </tr>
                </thead>
                <tbody>
        `;

        fornecedores.forEach((fornecedor, index) => {
            html += `
                <tr style="border-bottom: 1px solid #ddd;">
                    <td style="padding: 10px;">${fornecedor.nome}</td>
                    <td style="padding: 10px;">${fornecedor.cnpj}</td>
                    <td style="padding: 10px;">${fornecedor.telefone}</td>
                    <td style="padding: 10px; text-align: center;">
                        <button onclick="excluirFornecedor(${index})" style="background-color: var(--accent-color); padding: 5px 10px; font-size: 0.85rem;">
                            Excluir
                        </button>
                    </td>
                </tr>
            `;
        });

        html += `</tbody></table>`;
        tableContainer.innerHTML = html;
    }

    window.excluirFornecedor = function(index) {
        const fornecedores = JSON.parse(localStorage.getItem('fornecedores')) || [];
        fornecedores.splice(index, 1);
        localStorage.setItem('fornecedores', JSON.stringify(fornecedores));
        renderizarTabela();
    };


    form.addEventListener('submit', (e) => {
        e.preventDefault();

        if (inputCnpj.value.length < 18) {
            alert('Por favor, preencha o CNPJ completo.');
            return;
        }

        if (inputTelefone.value.length < 14) {
            alert('Por favor, preencha o Telefone completo.');
            return;
        }

        const novoFornecedor = {
            nome: inputNome.value.trim(),
            cnpj: inputCnpj.value.trim(),
            telefone: inputTelefone.value.trim()
        };

        const fornecedores = JSON.parse(localStorage.getItem('fornecedores')) || [];
        fornecedores.push(novoFornecedor);
        localStorage.setItem('fornecedores', JSON.stringify(fornecedores));

        alert('Fornecedor cadastrado com sucesso!');
        form.reset();
        renderizarTabela();
    });

    renderizarTabela();
});