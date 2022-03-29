<template>
  <v-container fluid class=" justify-center" fill-height>
    <v-card color="secondary" min-width="400" @keyup.enter="submit">

      <v-card-title class="justify-center">
        Portail
      </v-card-title>

      <v-form ref="form">
        <v-card-text>
          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="email"
                background-color="white"
                label="E-mail"
                outlined
                :rules="[rules.required, rules.emailFormat]"
                hide-details="auto"
              />
            </v-col>
          </v-row>

          <v-row>
            <v-col cols="12">
              <v-text-field
                v-model="password"
                background-color="white"
                outlined
                :append-icon="show ? 'mdi-eye' : 'mdi-eye-off'"
                :rules="[rules.required, rules.min]"
                :type="show ? 'text' : 'password'"
                label="Mot de passe"
                counter
                @click:append="show = !show"
              />
            </v-col>
          </v-row>
        </v-card-text>

        <v-divider />

        <v-card-actions>

          <v-btn color="primary" elevation="2" outlined rounded @click="$router.push('/inscription')">
            <v-icon left>
              mdi-pencil
            </v-icon>
            Inscription
          </v-btn>


          <v-spacer />

          <v-btn color="primary" rounded elevation="2" @click="submit">
            <v-icon left>
              mdi-fingerprint
            </v-icon>
            Se connecter
          </v-btn>
        </v-card-actions>

      </v-form>
    </v-card>
  </v-container>
</template>

<script>
export default {
  layout: 'empty',
  data() {
    return {
      show: false,
      password: '',
      email: '',
      rules: {
        required: (value) => !!value || 'Ce champ est requis',
        min: (v) => v.length >= 8 || 'Minimum 8 caractères',
        emailFormat: (v) => this.emailMatchFormat(v) || "L'e-mail n'est pas au bon format"
      },
    }
  },
  created() {
    if(this.$store.getters["session/isAuth"]) {
      this.$store.commit("session/resetUser")
    }
  },
  methods: {
    emailMatchFormat(email) {
      return String(email)
        .toLowerCase()
        .match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
    },
    async submit() {
      if (this.$refs.form.validate()) {
        this.$nuxt.$emit('overlay', true)
        try {
          await this.$store.dispatch('session/connexion', {
            email: this.email,
            password: this.password
          })
          await this.$router.push('/')
        } catch (e) {
          alert(e.message)
        } finally {
          this.$nuxt.$emit('overlay', false)
        }
      }
    }
  }
}
</script>

<style scoped lang="scss">
.acceuil:hover {
  cursor: pointer;
}
</style>
