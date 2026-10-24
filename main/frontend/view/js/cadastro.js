(() => {
  const form = document.getElementById("signup-form");
  const fields = {
    email: form.elements.email,
    username: form.elements.username,
    password: form.elements.password,
    confirm: form.elements.confirm,
  };

  const EMAIL_RE = /^[^\s@]+@[^\s@]+\.[^\s@]{2,}$/;
  const USERNAME_RE = /^[A-Za-z0-9_.]{3,20}$/;

  // Cada validador devolve uma mensagem de erro, ou "" quando o valor é válido.
  const validators = {
    email(emailCadastro) {
      if (!emailCadastro) return "Digite seu email";
      return EMAIL_RE.test(emailCadastro) ? "" : "Digite um email válido, como: exemplo@guardian.com";
    },
    username(usuarioCadastro) {
      if (!usuarioCadastro) return "Escolha um nome de usuário";
      return USERNAME_RE.test(usuarioCadastro)
        ? ""
        : "Use 3 a 20 caracteres: letras, números, _ ou .";
    },
    password(senhaCadastro) {
      if (!senhaCadastro) return "Crie uma Senha Mestra";
      return senhaCadastro.length >= 8 ? "" : "Use pelo menos 8 caracteres";
    },
    confirm(confirmaCadastro) {
      if (!confirmaCadastro) return "Repita sua Senha Mestre";
      return confirmaCadastro === fields.password.value ? "" : "As senhas não coincidem";
    },
  };

  function setState(name, message) {
    const wrapper = form.querySelector(`[data-field="${name}"]`);
    const input = fields[name];
    wrapper.querySelector(".field__msg").textContent = message;
    wrapper.classList.toggle("is-invalid", Boolean(message));
    wrapper.classList.toggle("is-valid", !message && input.value !== "");
    input.setAttribute("aria-invalid", message ? "true" : "false");
  }

  function validate(name) {
    const value = name === "password" || name === "confirm"
      ? fields[name].value
      : fields[name].value.trim();
    const message = validators[name](value);
    setState(name, message);
    return !message;
  }

  // Valida ao sair do campo e revalida enquanto digita, se já houver erro.
  Object.keys(fields).forEach((name) => {
    const input = fields[name];
    input.addEventListener("blur", () => validate(name));
    input.addEventListener("input", () => {
      const wrapper = form.querySelector(`[data-field="${name}"]`);
      if (wrapper.classList.contains("is-invalid")) validate(name);
      if (name === "password") {
        updateStrength(input.value);
        if (fields.confirm.value) validate("confirm");
      }
    });
  });

  // Medidor de força da senha (0 a 4)
  const meter = document.getElementById("strength");
  function updateStrength(pw) {
    let score = 0;
    if (pw.length >= 8) score++;
    if (pw.length >= 12) score++;
    if (/[a-z]/.test(pw) && /[A-Z]/.test(pw)) score++;
    if (/\d/.test(pw) && /[^A-Za-z0-9]/.test(pw)) score++;
    meter.dataset.level = pw ? Math.max(score, 1) : 0;
  }

  // Mostrar / ocultar senha
  form.querySelectorAll("[data-toggle]").forEach((btn) => {
    btn.addEventListener("click", () => {
      const input = fields[btn.dataset.toggle];
      const show = input.type === "password";
      input.type = show ? "text" : "password";
      btn.setAttribute("aria-pressed", String(show));
      btn.setAttribute("aria-label", show ? "Hide password" : "Show password");
    });
  });

  // Envio
  form.addEventListener("submit", (event) => {
    event.preventDefault();

    const results = Object.keys(fields).map((name) => validate(name));
    const firstInvalid = Object.keys(fields).find((_, i) => !results[i]);
    if (firstInvalid) {
      fields[firstInvalid].focus();
      return;
    }

    const submit = form.querySelector(".btn");
    submit.disabled = true;

    // TODO: enviar para o back-end. Nunca envie a senha em texto puro sem HTTPS.
    const payload = {
      email: fields.email.value.trim(),
      username: fields.username.value.trim(),
      password: fields.password.value,
    };
    console.log("Cadastro válido:", { ...payload, password: "••••••••" });

    submit.disabled = false;

    fetch("${GUARDIAN_URL}/cadastrar", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
    })
    .then(response => {
        if (!response.ok) {
            throw new Error("Erro ao cadastrar usuário");
        }

        return response.json();
    })
    .then(data => {
        console.log("Cadastro realizado:", data);
    })
    .catch(error => {
        console.error("Erro na requisição:", error);
    });
  });
});
