const formLogin = document.querySelector('#login');

formLogin.addEventListener('submit', async (evento) => {
    evento.preventDefault();

    const email = document.querySelector('#email').value;
    const senha = document.querySelector('#pw').value;

    const response = await fetch(`${GUARDIAN_URL}/login`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        credentials: 'include',
        body: JSON.stringify({ email, senha })
    });

    if (response.ok) {
        window.location.href = 'telaPerfil.html';
    } else {
        alert('E-mail ou senha incorretos.');
    }
});

const botaoEsqueceuSenha = document.querySelector('#esqueceuSenha');

botaoEsqueceuSenha.addEventListener('click', () => {}); // FALTA IMPLEMENTAR