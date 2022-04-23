<template>
  <v-container>
    <v-row justify="right" align="right">
      <v-col cols="12">

        <v-card v-if=!isEdit
            class="mx-auto"
            max-width="344"
        >
            <v-card-title>
            Developpeur fullstack
            </v-card-title>

            <v-spacer></v-spacer>

            <v-card-subtitle>
                <v-icon>mdi-calendar-blank</v-icon>
                <v-text v-model="date1" />
            </v-card-subtitle>

            <v-card-subtitle>
                <v-icon>mdi-map-marker</v-icon>
                Paris
            </v-card-subtitle>

            <v-card-actions>
                <v-btn
                    color="orange lighten-2"
                    text
                    @click.stop="isEdit = true"
                >
                    Modifier
                </v-btn>

                <v-spacer></v-spacer>

                <v-btn
                    icon
                    @click="show = !show"
                >
                    <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                </v-btn>
                </v-card-actions>

                <v-expand-transition>
                <div v-show="show">
                    <v-divider></v-divider>

                    <v-card-text>
                    Expérience de 5 mois en tant que développeur fullstack au sein de l'entreprise X. J'ai travaillé avec Y outils et Z framework... etc.
                    </v-card-text>
                </div>
                </v-expand-transition>
        </v-card>

        <v-card v-if=isEdit
            class="mx-auto"
            max-width="344"
        >
            <v-card-title>
            Developpeur fullstack - modification en cours
            </v-card-title>

            <v-spacer></v-spacer>

            <v-card-subtitle>
                <v-icon>mdi-calendar-blank</v-icon>
                <v-text-field v-model="date1" />
            </v-card-subtitle>

            <v-card-subtitle>
                <v-icon>mdi-map-marker</v-icon>
                Paris
            </v-card-subtitle>

            <v-card-actions>
                <v-btn
                    color="success"
                    text
                    @click.stop="isEdit = false"
                >
                    Valider
                </v-btn>

                <v-spacer></v-spacer>

                <v-btn
                    icon
                    @click="show = !show"
                >
                    <v-icon>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                </v-btn>
                </v-card-actions>

                <v-expand-transition>
                <div v-show="show">
                    <v-divider></v-divider>

                    <v-card-text>
                    Expérience de 5 mois en tant que développeur fullstack au sein de l'entreprise X. J'ai travaillé avec Y outils et Z framework... etc.
                    </v-card-text>
                </div>
                </v-expand-transition>
        </v-card>

      </v-col>
    </v-row>
  </v-container>
</template>

<script>
  export default {
    data: () => ({
      show: false,
      isEdit: false,
      date1: "25/05 au 25/10"
    }),
  }
</script>

