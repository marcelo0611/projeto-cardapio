document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');

    form.addEventListener('submit', function(event) {
        event.preventDefault(); 

        const nome = document.getElementById('nome-prato').value;
        const ingredientes = document.getElementById('ingredientes').value;
        const peso = document.getElementById('peso').value;

        const novoPrato = { nome, ingredientes, peso };

        let listaPratos = JSON.parse(localStorage.getItem('pratos')) || [];
        
        listaPratos.push(novoPrato);
        localStorage.setItem('pratos', JSON.stringify(listaPratos));

        alert('Prato cadastrado com sucesso!');
        form.reset(); 
    });
});