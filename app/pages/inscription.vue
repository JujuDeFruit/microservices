<template>
  <v-container fluid class="justify-center" fill-height>
    <v-card color="secondary" min-width="400" @keyup.enter="submit">

      <v-card-title class="justify-center">
        Inscription
      </v-card-title>

      <v-form ref="form">
        <v-card-text>
          <v-text-field
            v-model="email"
            background-color="white"
            outlined
            :rules="email ? [rules.required, v => emailMatchFormat(v) || `L'email n'est pas au bon format`] : []"
            label="Email"
          />
          <v-text-field
            v-model="password"
            background-color="white"
            outlined
            :append-icon="show1 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="password ? [rules.required, rules.min] : []"
            :type="show1 ? 'text' : 'password'"
            label="Mot de passe"
            counter
            @click:append="show1 = !show1"
          />
          <v-text-field
            v-model="password2"
            background-color="white"
            outlined
            :append-icon="show2 ? 'mdi-eye' : 'mdi-eye-off'"
            :rules="password2 ? [rules.required, rules.min] : []"
            :type="show2 ? 'text' : 'password'"
            label="Confirmer mot de passe"
            counter
            @click:append="show2 = !show2"
          />
        </v-card-text>

        <v-divider />

        <v-card-actions>
          <v-btn color="primary" outlined fab elevation="5" @click="$router.push('/connexion')">
            <v-icon>
              mdi-arrow-left-bold-outline
            </v-icon>
          </v-btn>
          <v-spacer />
          <v-btn color="primary" elevation="5" rounded @click="submit">
            <v-icon left>
              mdi-pencil
            </v-icon>
            S'inscrire
          </v-btn>
        </v-card-actions>
      </v-form>
    </v-card>
  </v-container>
</template>

<script>
export default {
  layout: 'empty',
  data: () => {
    return {
      email: '',
      password: '',
      password2: '',
      show1: false,
      show2: false,
      rules: {
        required: value => !!value || 'Ce champ est requis',
        min: v => v.length >= 8 || 'Minimum 8 caractères',
      },
    }
  },
  methods: {
    emailMatchFormat(email) {
      return String(email)
        .toLowerCase()
        .match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
    },
    async submit() {
      if(this.$refs.form.validate()) {
        if (this.password === this.password2) {
          this.$nuxt.$emit('overlay', true)
          const user = {
            email: this.email,
            password: this.password
          }
          try {
            await this.$store.dispatch('session/inscription', user)
          } catch (e) {
            alert(e.message)
          } finally {
            this.$nuxt.$emit('overlay', false)
          }
        } else {
          alert("Les 2 mots de passe ne matchent pas !")
        }
      }
    }
  },
}
</script>
