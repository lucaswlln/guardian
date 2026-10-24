import { GUARDIAN_URL } from "../config.js";

const botaoTrocar = document.querySelector('#trocarIMG');
const fundoModal = document.querySelector('#fundoModal');

botaoTrocar.addEventListener('click', () => {
    fundoModal.classList.toggle('oculto');
});

// Fecha ao clicar fora do painel e esc
fundoModal.addEventListener('click', (evento) => {
    if (evento.target === fundoModal) {
        fundoModal.classList.add('oculto');
    }
});
document.addEventListener('keydown', (evento) => {
    if (evento.key === 'Escape') {
        fundoModal.classList.add('oculto');
    }
});

const email = document.querySelector('#emailUsuario');
const qtdContas = document.querySelector('#qtdContas');
const cabecalhoNome = document.querySelector('#cabecalho');

fetch(`${GUARDIAN_URL}/usuario/info`, {
    credentials: "include"
})
    .then(resposta => {
        if (!resposta.ok) {
            throw new Error(`Erro HTTP: ${resposta.status}`);
        }

        return resposta.json();
    })
    .then(info => {
        email.textContent = `Email: ${info.emailUsuario}`;
        qtdContas.textContent =
            `Quantidade de contas salvas: ${info.qtdContasUsuario}`;
        cabecalhoNome.textContent = `Bem-vindo, ${info.nomeUsuario}`;
    })
    .catch(erro => {
        console.error('Erro ao carregar o perfil:', erro);
    });
