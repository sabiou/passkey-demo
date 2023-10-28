package com.godi.passkey_demo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.godi.passkey_demo.ui.theme.PasskeydemoTheme

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun SignUpScreen() {
    var fullname by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var acceptedTerms by remember { mutableStateOf(true) }


    val focus = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val onSubmit: () -> Unit = {
        TODO("Handle onSubmit")
    }

    val onSignUpWithPasskeySubmit: () -> Unit = {
        TODO("Handle onSignUpWithPasskeySubmit")
    }

    @Composable
    fun TermsAndConditions(onClick: () -> Unit) {
        val fullText = "I accept the Terms & Conditions"
        val clickableText = "Terms & Conditions"
        val tag = "terms-and-conditions"

        val annotatedString = buildAnnotatedString {
            append(fullText)
            val start = fullText.indexOf(clickableText)
            val end = start + clickableText.length

            addStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                ),
                start = start,
                end = end
            )

            addStringAnnotation(
                tag = tag,
                annotation = "https://www.composables.com",
                start = start,
                end = end
            )
        }
        val uriHandler = LocalUriHandler.current
        ClickableText(
            style = MaterialTheme.typography.bodyMedium.copy(
                color = LocalContentColor.current
            ),
            text = annotatedString,
            onClick = { offset ->
                val string = annotatedString.getStringAnnotations(tag, offset, offset).firstOrNull()
                if (string != null) {
                    uriHandler.openUri(string.item)
                } else {
                    onClick()
                }
            }
        )
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .verticalScroll(rememberScrollState(0)),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 600.dp)
                    .padding(horizontal = 16.dp)
                    .padding(vertical = 32.dp),
            ) {
                Text(
                    text = "Create Free Account",
                    style = MaterialTheme.typography.headlineMedium,
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = fullname,
                    label = { Text("Full name") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Next)
                        }
                    ),
                    onValueChange = { fullname = it },
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = username,
                    label = { Text("E-mail") },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.moveFocus(FocusDirection.Next)
                        }
                    ),
                    onValueChange = { username = it },
                    singleLine = true
                )
                Spacer(Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Password") },
                    isError = errorMessage.isNotBlank(),
                    supportingText = {
                        Text(errorMessage)
                    },
                    value = password,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = {
                            focus.clearFocus()
                            keyboardController?.hide()
                            onSubmit()
                        }
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    onValueChange = {
                        password = it
                    },
                    singleLine = true
                )

                Spacer(Modifier.height(16.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(MaterialTheme.shapes.small)
                        .clickable { acceptedTerms = acceptedTerms.not() }
                        .padding(horizontal = 2.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    Checkbox(
                        checked = acceptedTerms,
                        onCheckedChange = null,
                    )
                    TermsAndConditions(onClick = { acceptedTerms = acceptedTerms.not() })
                }

                Spacer(Modifier.height(16.dp))
                Column(Modifier.padding(horizontal = 16.dp)) {
                    Button(
                        enabled = acceptedTerms && fullname.isNotBlank()
                                && username.isNotBlank()
                                && password.isNotBlank(),
                        onClick = onSubmit,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Text("Sign Up")
                    }
                    Spacer(Modifier.height(8.dp))
                    TextButton(
                        modifier = Modifier.align(Alignment.CenterHorizontally),
                        onClick = { /*TODO*/ },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.onSurface.copy(
                                alpha = 0.66f
                            )
                        )
                    ) {
                        Text("Already have an account? Sign in")
                    }
                    Spacer(Modifier.height(8.dp))
                    Button(
                        onClick = onSignUpWithPasskeySubmit,
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Icon(painter = painterResource(id = R.drawable.icon_passkey), contentDescription = null)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text("Sign up with passkey")
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun SignUpScreenPreview() {
    PasskeydemoTheme {
        SignUpScreen()
    }
}