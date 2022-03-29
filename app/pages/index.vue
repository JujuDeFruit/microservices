<template>
  <v-container>
    <v-row justify="center" align="center">
      <v-col cols="12">
        <v-card v-if="!isEdit && header" max-height="85vh">

          <v-card-title>Mes données personnelles</v-card-title>

          <v-card-text>

            <v-row>
              <v-col cols="4">
                <v-input label="Nom" />
              </v-col>
              <v-col cols="8">
                <v-input v-if="header.nom" :label="header.nom" />
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="4">
                <v-input label="Prénom" />
              </v-col>
              <v-col cols="8">
                <v-input v-if="header.prenom" :label="header.prenom" />
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="4">
                <v-input label="Adresse" />
              </v-col>
              <v-col cols="8">
                <v-input v-if="header.adresse" :label="adresse" />
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="4">
                <v-input label="Ville" />
              </v-col>
              <v-col cols="8">
                <v-input v-if="header.adresse" :label="ville" />
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="4">
                <v-input label="Code postal" />
              </v-col>
              <v-col cols="8">
                <v-input v-if="header.adresse" :label="codePostal" />
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="4">
                <v-input label="Email" />
              </v-col>
              <v-col cols="8">
                <v-input v-if="header.email" :label="header.email" />
              </v-col>
            </v-row>

            <v-row>
              <v-col cols="4">
                <v-input label="Téléphone" />
              </v-col>
              <v-col cols="8">
                <v-input v-if="header.tel" :label="header.tel" />
              </v-col>
            </v-row>

          </v-card-text>

          <v-card-actions>
            <v-spacer />
            <v-btn color="error" rounded @click.stop="preModification">
              <v-icon left>mdi-pencil</v-icon>
              Modifier
            </v-btn>
          </v-card-actions>

        </v-card>

        <v-card v-if="isEdit && header" max-height="85vh">

          <v-form ref="form">

            <v-card-text>

              <v-row>
                <v-col cols="4">
                  <v-input label="Nom" />
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    v-model="data.nom"
                    class="pt-0 mt-0"
                    label="Nom"
                    :rules="[rules.required]"
                  />
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="4">
                  <v-input label="Prénom" />
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    v-model="data.prenom"
                    class="pt-0 mt-0"
                    label="Prénom"
                    :rules="[rules.required]"
                  />
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="4">
                  <v-input label="Adresse" />
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    v-model="data.adresse"
                    class="pt-0 mt-0"
                    label="Adresse"
                    :rules="[rules.required]"
                  />
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="4">
                  <v-input label="Ville" />
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    v-model="data.ville"
                    class="pt-0 mt-0"
                    label="Ville"
                    :rules="[rules.required]"
                  />
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="4">
                  <v-input label="Code postal" />
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    v-model="data.codePostal"
                    class="pt-0 mt-0"
                    label="Code postal"
                    :rules="[rules.required]"
                  />
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="4">
                  <v-input label="Email" />
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    v-model="data.email"
                    :rules="[rules.emailFormat, rules.required]"
                    class="pt-0 mt-0"
                    label="Email"
                  />
                </v-col>
              </v-row>

              <v-row>
                <v-col cols="4">
                  <v-input label="Téléphone" />
                </v-col>
                <v-col cols="8">
                  <v-text-field
                    v-model="data.tel"
                    class="pt-0 mt-0"
                    label="Téléphone"
                    :rules="[rules.required, rules.telFormat]"
                  />
                </v-col>
              </v-row>

            </v-card-text>

            <v-card-actions>
              <v-btn color="error" rounded @click.stop="isEdit = false">
                Annuler
              </v-btn>
              <v-spacer />
              <v-btn color="success" rounded @click.stop="modifier">
                Valider les modifications
              </v-btn>
            </v-card-actions>

          </v-form>

        </v-card>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import {MICRO_HEADER} from "@/constant/api";

export default {
  middleware: 'auth',
  async asyncData(ctx) {
    let header = {}
    try {
      header = await ctx.$axios.get(MICRO_HEADER.concat('/cv/header'), {
        params: {
          id: ctx.store.getters["session/getId"]
        }
      })
    } catch (e) {
      alert(e.message)
    }

    return { header: header.data }
  },
  data() {
    return {
      isEdit: false,
      data: {
        nom: '',
        prenom: '',
        adresse: '',
        ville: '',
        codePostal: '',
        email: '',
        tel: ''
      },
      rules: {
        required: (value) => !!value || 'Ce champ est equis',
        emailFormat: (v) => this.emailMatchFormat(v) || "L'e-mail n'est pas au bon format",
        telFormat: (t) => String(t).match(/^\d{10}$/) || "Le téléphone n'est pas valide",
      }
    }
  },
  computed: {
    adresse() {
      return this.header && this.header.adresse ? this.header.adresse.split('/').at(0).trim() : ""
    },
    ville() {
      return this.header && this.header.adresse ? this.header.adresse.split('/').at(1).trim() : ""
    },
    codePostal() {
      return this.header && this.header.adresse ? this.header.adresse.split('/').at(-1).trim() : ""
    }
  },
  methods: {
    emailMatchFormat(email) {
      return String(email)
        .toLowerCase()
        .match(/^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/)
    },
    preModification() {
      // Copy data to temp variable
      this.data.nom = this.header.nom ?? ""
      this.data.prenom = this.header.prenom ?? ""
      this.data.email = this.header.email ?? ""
      this.data.tel = this.header.tel ?? ""
      this.data.adresse = this.adresse ?? ""
      this.data.ville = this.ville ?? ""
      this.data.codePostal = this.codePostal ?? ""
      this.isEdit = true
    },
    async modifier() {

      const equal = () => {
        return this.data.nom === this.header.nom
          && this.data.prenom === this.header.prenom
          && this.data.adresse === this.adresse
          && this.data.ville === this.ville
          && this.data.codePostal === this.codePostal
          && this.data.email === this.header.email
          && this.data.tel === this.header.tel
      }

      if(equal()) {
        this.isEdit = false
        return
      }

      if(this.$refs.form.validate() && !equal()) {
        const tmp = { ...this.data }
        tmp.adresse = this.data.adresse + ' / ' + this.data.ville + ' / ' + this.data.codePostal

        this.$nuxt.$emit('overlay', true)
        try {
          await this.$axios.post(MICRO_HEADER.concat('/cv/modifier'), { ...tmp })
          this.header = tmp
        } catch (e) {
          alert(e)
        } finally {
          this.$nuxt.$emit('overlay', false)
          this.isEdit = false
        }
      }
    }
  }
}
</script>

