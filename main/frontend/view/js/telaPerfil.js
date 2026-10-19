const botaoTrocar = document.querySelector('#trocarIMG');
const fundoModal = document.querySelector('#fundoModal');

botaoTrocar.addEventListener('click', () => {
    fundoModal.classList.toggle('oculto');
});

// Fecha ao clicar fora do painel
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

const usuario = // pega usuario no bd


cabecalhoNome.textContent = `Bem vindo ${usuario.getNome}`
qtdContas.textContent = `Quantidade de contas salvas: ${usuario.getQtdSenhas}`
email.textContent = `Email: ${usuario.getEmail}`