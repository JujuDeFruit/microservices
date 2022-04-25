<template>
<v-container>
    <v-row justify="right" align="right">
        <v-col cols="12">

            <v-card v-if=!isEdit class="mx-auto" max-width="1000">
                <v-card-title>
                    Mes Compétences
                </v-card-title>

                <v-spacer></v-spacer>

                <v-card-text>

                    <v-row v-for="(array_competence,k) in competence.competence" :key="k">
                        <v-col cols="4">
                            <v-input :label="array_competence.split('/')[0].trim()" />
                        </v-col>
                        <v-col cols="3">
                            <v-icon>mdi-chevron-right</v-icon>
                        </v-col>
                        <v-col cols="4">
                            {{array_competence.split('/')[1].trim()}}
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

            <v-card v-if=isEdit class="mx-auto" max-width="1000">
                <v-form ref="form">
                    <v-card-title>
                        Mes Compétences
                    </v-card-title>

                    <v-spacer></v-spacer>

                    <v-card-text>

                        <v-row v-for="(array_competence,k) in data.temp_competence" :key="k">
                            <v-col cols="4">
                                <v-text-field v-model="data.temp_competence[k][0]" :rules="[rules.required]" label="Titre de la compétence" dense />
                            </v-col>
                            <v-col cols="1">
                                <v-icon>mdi-chevron-right</v-icon>
                            </v-col>
                            <v-col cols="4">
                                <v-text-field v-model="data.temp_competence[k][1]" :rules="[rules.required]" label="Niveau de la compétence" dense></v-text-field>
                            </v-col>
                            <v-col cols="1">
                                <v-btn color="primary" depressed @click="deleteRow(k,data.temp_competence[k])">
                                    <v-icon left>mdi-delete</v-icon>Supprimer
                                </v-btn>
                            </v-col>
                        </v-row>
                    </v-card-text>

                    <v-card-actions>
                        <v-btn class="mx-2" fab dark color="indigo" @click.stop="addElem">
                            <v-icon dark>
                                mdi-plus
                            </v-icon>
                        </v-btn>
                        <v-spacer />
                        <v-btn color="success" @click.stop="modifier">
                            <v-icon left>mdi-check-bold</v-icon>
                            Enregistrer
                        </v-btn>
                        <v-btn color="error" @click.stop="isEdit = false">
                            <v-icon left>mdi-close</v-icon>
                            Annuler
                        </v-btn>
                    </v-card-actions>
                </v-form>
            </v-card>
        </v-col>
    </v-row>
</v-container>
</template>

<script>
import {
    MICRO_COMPETENCES
} from "@/constant/api";

export default {
    middleware: 'auth',
    async asyncData(ctx) {
        let competence = {}
        try {
            competence = await ctx.$axios.get(MICRO_COMPETENCES.concat('/cv/competences'), {
                params: {
                    id: ctx.store.getters["session/getId"]
                }
            })
        } catch (e) {
            alert(e.message)
        }
        console.log(competence)
        console.log(competence.data)
        return {
            competence: competence.data
        }
    },
    data: () => ({
        show: false,
        isEdit: false,
        data: {
            temp_competence: []
        },
        rules: {
            required: (value) => !!value || 'Ce champ est requis',
        },
    }),

    methods: {
        preModification() {
            this.data.temp_competence = []
            if(this.competence.competence!=null)
            this.competence.competence.forEach(competence => {
                this.data.temp_competence.push([competence.split('/')[0].trim(), competence.split('/')[1].trim()])
            });
            this.isEdit = true
        },
        addElem() {
            this.data.temp_competence.push(["", ""])
        },
        deleteRow(index, competence) {
            this.data.temp_competence.splice(index, 1)
        },
        async modifier() {
            if (this.$refs.form.validate()) {
                const tmp = {
                }
                tmp.competence=[]
                tmp.id = this.$store.getters['session/getId']
                console.log(tmp)
                this.data.temp_competence.forEach(element => {
                    tmp.competence.push(element[0].concat('/',element[1]))
                });
                console.log(tmp)
                this.$nuxt.$emit('overlay', true)
                try {
                    await this.$axios.post(MICRO_COMPETENCES.concat('/cv/modifier'), {
                        ...tmp
                    })
                    this.competence = tmp
                } catch (e) {
                    alert(e)
                } finally {
                    this.$nuxt.$emit('overlay', false)
                    this.isEdit = false
                }
            }

        },

    }
}
</script>
