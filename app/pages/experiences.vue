<template>
  <v-container>
    <v-row justify="right" align="right">
      <v-col cols="12">

        <v-card v-if=!isEdit
            class="mx-auto"
            max-width="500"
        >
            <v-card-title>
             {{titre}}
            </v-card-title>

            <v-spacer></v-spacer>

            <v-card-subtitle>
                <v-row>
                    <v-col md="auto"> 
                        <v-icon>mdi-calendar-blank</v-icon>
                    </v-col>
                    <v-col md="auto"> 
                        <v-text-field v-model="date1" readonly label="De" 
                        width="20"/>
                    </v-col>
                    <v-col md="auto">
                        <v-text-field v-model="date2" readonly label="À"/>
                    </v-col>
                </v-row>
            </v-card-subtitle>

            <v-card-subtitle>
                <v-row>
                    <v-col md="auto"> 
                        <v-icon>mdi-map-marker</v-icon>
                    </v-col>
                    <v-col md="auto"> 
                        {{lieu}}
                    </v-col>
                </v-row>
            </v-card-subtitle>

            <v-card-actions>
            <v-btn color="error" rounded @click.stop="preModification">
              <v-icon left>mdi-pencil</v-icon>
              Modifier
            </v-btn>

                <v-spacer></v-spacer>

                <v-btn
                    text
                    @click="show = !show"
                >
                    Détail
                    <v-icon left>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                </v-btn>
                </v-card-actions>

                <v-expand-transition>
                <div v-show="show">
                    <v-divider></v-divider>

                    <v-card-text >
                    {{detail}}
                    </v-card-text>
                </div>
                </v-expand-transition>
        </v-card>

        <v-card v-if=isEdit
            class="mx-auto"
            max-width="500"
        >
            <v-card-title>
                <v-row>
                    <v-col md="auto"> 
                        <v-text-field v-model="titre"/> 
                    </v-col>
                </v-row>
            </v-card-title>
            <v-spacer></v-spacer>

            <v-card-subtitle>
                <v-row>
                    <v-col md="auto"> 
                        <v-icon>mdi-calendar-blank</v-icon>
                    </v-col>
                    <v-col md="auto"> 
                        <v-text-field v-model="date1" label="De"/>
                    </v-col>
                    <v-col md="auto">
                        <v-text-field v-model="date2" label="À"/>
                    </v-col>
                </v-row>
            </v-card-subtitle>

            <v-card-subtitle>
                <v-row>
                    <v-col md="auto"> 
                        <v-icon>mdi-map-marker</v-icon>
                    </v-col>
                    <v-col md="auto"> 
                        <v-text-field v-model="lieu" dense />
                    </v-col>
                </v-row>
            </v-card-subtitle>

            <v-card-actions>
              <v-btn color="error" rounded @click.stop="isEdit = false">
                Annuler
              </v-btn>
              <v-spacer />
              <v-btn color="success" rounded @click.stop="modifier">
                Valider les modifications
              </v-btn>

                <v-spacer></v-spacer>

                <v-btn
                    text
                    @click="show = !show"
                >
                    Détail
                    <v-icon left>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                </v-btn>
                </v-card-actions>

                <v-expand-transition>
                <div v-show="show">
                    <v-divider></v-divider>

                    <v-card-text >
                    <v-textarea v-model="detail" dense />
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
      date1: "05/2020",
      date2: "25/2022",
      lieu : "Paris - 75000",
      fromDateMenu : false,
      titre : "FullStack",
      detail :"Expérience de 5 mois en tant que développeur fullstack au sein de l'entreprise X. J'ai travaillé avec Y outils et Z framework... etc."
    }),
    methods: {
        preModification() {
            this.isEdit = true
        },
        modifier()
        {
            this.isEdit=false
        },
    }
  }
</script>