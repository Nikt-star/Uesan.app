package com.example.uesanapp.presentation.presentation.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun RegisterScreen(
    onNavigateToLogin: () -> Unit,
    onRegisterSuccess: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Validaciones
    val isNameValid = name.isNotBlank()
    val isEmailValid = android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val passwordsMatch = password == confirmPassword && password.isNotEmpty()
    val isFormValid = isNameValid && isEmailValid && passwordsMatch

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
            .statusBarsPadding(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Crear Cuenta",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )

        Text(
            text = "Regístrate para empezar",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Campo Nombre (Solo letras y espacios)
        OutlinedTextField(
            value = name,
            onValueChange = { newValue ->
                if (newValue.all { it.isLetter() || it.isWhitespace() }) {
                    name = newValue
                }
            },
            label = { Text("Nombre Completo") },
            leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            isError = name.isNotEmpty() && !isNameValid
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Email
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo Electrónico") },
            leadingIcon = { Icon(Icons.Default.Email, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            isError = email.isNotEmpty() && !isEmailValid,
            supportingText = {
                if (email.isNotEmpty() && !isEmailValid) {
                    Text("Introduce un correo válido")
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Contraseña
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Contraseña") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Campo Confirmar Contraseña
        OutlinedTextField(
            value = confirmPassword,
            onValueChange = { confirmPassword = it },
            label = { Text("Confirmar Contraseña") },
            leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation(),
            singleLine = true,
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            isError = confirmPassword.isNotEmpty() && !passwordsMatch,
            supportingText = {
                if (confirmPassword.isNotEmpty() && !passwordsMatch) {
                    Text("Las contraseñas no coinciden")
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Botón Registrar (Se habilita solo si el formulario es válido)
        Button(
            onClick = onRegisterSuccess,
            enabled = isFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            shape = MaterialTheme.shapes.medium
        ) {
            Text("Registrarse", fontWeight = FontWeight.Bold)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Opción para retroceder al Login
        TextButton(onClick = onNavigateToLogin) {
            Text("¿Ya tienes cuenta? Inicia sesión")
        }
    }
}
