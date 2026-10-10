const formLogin = document.querySelector('#login');
formLogin.addEventListener('submit', async (evento)=>{
    evento.preventDefault();

    const email = document.querySelector('#email').value;
    const senha = document.querySelector('#pw').value;

    const response = await fetch( GUARDIAN_URL, {
        method: 'POST',
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            email: email,
            senha: senha
        })
    });
});

const botaoEsqueceuSenha = document.querySelector('#esqueceuSenha');
botaoEsqueceuSenha.addEventListener('click', ()=>{});