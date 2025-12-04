# Gerador de Avatar - Android

Aplicativo Android nativo desenvolvido em Kotlin que permite criar, visualizar, editar e deletar avatares gerados por IA através de prompts de texto.

## Funcionalidades

- **Criar avatares** a partir de descrições em texto
- **Editar prompts** de avatares existentes
- **Deletar avatares** da galeria
- **Listar todos os avatares** em uma lista interativa
- **Visualizar imagens** geradas pela IA

## Tecnologias Utilizadas

- **Kotlin** - Linguagem de programação principal
- **Android SDK** - API Level 24 (Android 7.0) ou superior
- **Retrofit 2.9.0** - Cliente HTTP para consumo de API REST
- **Gson** - Serialização/deserialização JSON
- **Glide 4.16.0** - Carregamento e cache de imagens
- **RecyclerView** - Exibição eficiente de listas
- **Material Design** - Interface moderna e responsiva

## Arquitetura

O projeto segue uma estrutura limpa com separação de responsabilidades:

```
app/src/main/java/com/example/geradoravatar/
├── MainActivity.kt          # Activity principal com lógica de UI
├── AvatarApiService.kt      # Interface Retrofit para API
├── AvatarAdapter.kt         # Adapter do RecyclerView
└── Avatar.kt                # Classes de dados (Avatar, AvatarRequest)
```

## API REST

O aplicativo se comunica com uma API backend (Flask) rodando localmente:

**Base URL:** `http://10.0.2.2:5000/` (para emulador Android)

### Endpoints:

- `GET /avatares` - Lista todos os avatares
- `POST /avatares` - Cria novo avatar
  ```json
  { "prompt": "descrição do avatar" }
  ```
- `PUT /avatares/{id}` - Atualiza avatar existente
  ```json
  { "prompt": "nova descrição" }
  ```
- `DELETE /avatares/{id}` - Remove avatar

## Como Executar

### Pré-requisitos

- Android Studio Arctic Fox ou superior
- JDK 11
- Dispositivo Android ou Emulador com API Level 24+
- Backend da API rodando (veja seção Backend)

### Passos

1. **Clone o repositório:**
   ```bash
   git clone https://github.com/desatinar/android-gerador-avatar.git
   cd android-gerador-avatar
   ```

2. **Abra o projeto no Android Studio**

3. **Sincronize as dependências:**
   - O Gradle irá baixar automaticamente todas as dependências

4. **Configure o backend:**
   - Certifique-se de que a API está rodando em `http://localhost:5000`
   - Para emulador: use `10.0.2.2:5000`
   - Para dispositivo físico: altere para o IP da máquina na rede local

5. **Execute o app:**
   - Conecte um dispositivo Android ou inicie o emulador
   - Clique em "Run" no Android Studio

## Configuração

### Alterar URL da API

Em `MainActivity.kt`, modifique a base URL do Retrofit:

```kotlin
private val retrofit = Retrofit.Builder()
    .baseUrl("http://SEU_IP:5000/") // Altere aqui
    .addConverterFactory(GsonConverterFactory.create())
    .build()
```

## Dependências

```gradle
dependencies {
    // Core Android
    implementation("androidx.core:core-ktx")
    implementation("androidx.appcompat:appcompat")
    implementation("com.google.android.material:material")
    implementation("androidx.constraintlayout:constraintlayout")
    
    // Networking
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    
    // Image Loading
    implementation("com.github.bumptech.glide:glide:4.16.0")
}
```

## Requisitos do Sistema

- **Min SDK:** 24 (Android 7.0 Nougat)
- **Target SDK:** 36 (Android 14+)
- **Compile SDK:** 36
- **JVM Target:** 11

## Permissões

O app requer a seguinte permissão no `AndroidManifest.xml`:

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## Funcionalidades em Detalhes

### Criação de Avatar
1. Digite uma descrição do avatar no campo de texto
2. Clique em "CRIAR AVATAR"
3. Aguarde o processamento
4. O avatar aparecerá na lista

### Edição de Avatar
1. Clique no botão "Editar" de um avatar existente
2. O prompt será preenchido no campo de texto
3. Modifique conforme necessário
4. Clique em "SALVAR ALTERAÇÃO"

### Exclusão de Avatar
1. Clique no botão "Deletar" no avatar desejado
2. O avatar será removido imediatamente

## Backend (Requisito)

Este aplicativo requer um backend Flask rodando. Certifique-se de ter a API configurada e rodando antes de usar o app.

## Contribuindo

Contribuições são bem-vindas! Sinta-se à vontade para:

1. Fazer fork do projeto
2. Criar uma branch para sua feature (`git checkout -b feature/NovaFuncionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/NovaFuncionalidade`)
5. Abrir um Pull Request

## Licença

Este projeto é de código aberto e está disponível para uso educacional.

## Autor

**desatinar**

- GitHub: [@desatinar](https://github.com/desatinar)
- Repositório: [android-gerador-avatar](https://github.com/desatinar/android-gerador-avatar)

## Screenshots

_Em breve: adicione capturas de tela do aplicativo aqui_

---

Se este projeto foi útil para você, considere dar uma estrela no repositório!
