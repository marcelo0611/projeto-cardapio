const API = "http://localhost:8080/chefs";

const form = document.getElementById("formChef");
const tbody = document.querySelector("#tabelaChefs tbody");

form.addEventListener("submit", salvarChef);

let idEdicao = null;


async function listarChefs() {

    try {

        const resposta = await fetch(API);

        if (!resposta.ok) {
            throw new Error("Erro ao buscar chefs.");
        }

        const chefs = await resposta.json();

        tbody.innerHTML = "";

        chefs.forEach(chef => {

            tbody.innerHTML += `
                <tr>
                    <td>${chef.id}</td>
                    <td>${chef.nome}</td>
                    <td>${chef.especialidade}</td>
                    <td>${chef.telefone}</td>
                    <td>${chef.email}</td>
                    <td>
                        <button onclick="editarChef(${chef.id})">
                            Editar
                        </button>

                        <button onclick="excluirChef(${chef.id})">
                            Excluir
                        </button>
                    </td>
                </tr>
            `;

        });

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar os chefs.");

    }

}


async function salvarChef(event) {

    event.preventDefault();

    const chef = {

        nome: document.getElementById("nome").value,

        especialidade: document.getElementById("especialidade").value,

        telefone: document.getElementById("telefone").value,

        email: document.getElementById("email").value

    };

    try {

        let resposta;

        if (idEdicao == null) {

            resposta = await fetch(API, {

                method: "POST",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(chef)

            });

        } else {

            resposta = await fetch(API + "/" + idEdicao, {

                method: "PUT",

                headers: {
                    "Content-Type": "application/json"
                },

                body: JSON.stringify(chef)

            });

        }

        if (resposta.ok) {

            alert(idEdicao == null
                ? "Chef cadastrado com sucesso!"
                : "Chef atualizado com sucesso!");

            form.reset();

            idEdicao = null;

            listarChefs();

        } else {

            alert("Erro ao salvar chef.");

        }

    } catch (erro) {

        console.error(erro);

        alert("Erro de conexão com o servidor.");

    }

}



async function editarChef(id) {

    try {

        const resposta = await fetch(API + "/" + id);

        const chef = await resposta.json();

        document.getElementById("nome").value = chef.nome;

        document.getElementById("especialidade").value = chef.especialidade;

        document.getElementById("telefone").value = chef.telefone;

        document.getElementById("email").value = chef.email;

        idEdicao = id;

        window.scrollTo({
            top: 0,
            behavior: "smooth"
        });

    } catch (erro) {

        console.error(erro);

        alert("Erro ao carregar o chef.");

    }

}



async function excluirChef(id) {

    const confirmar = confirm("Deseja realmente excluir este chef?");

    if (!confirmar) {

        return;

    }

    try {

        const resposta = await fetch(API + "/" + id, {

            method: "DELETE"

        });

        if (resposta.ok) {

            alert("Chef excluído com sucesso!");

            listarChefs();

        } else {

            alert("Erro ao excluir.");

        }

    } catch (erro) {

        console.error(erro);

        alert("Erro ao conectar ao servidor.");

    }

}


listarChefs();