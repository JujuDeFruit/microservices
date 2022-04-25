<template>
<v-container>
    <v-row justify="right" align="right">
        <v-col cols="12">

            <v-card v-if=!isEdit class="mx-auto" max-width="1000">
                <v-card-title>
                    Formation Scolaire
                </v-card-title>

                <v-spacer></v-spacer>

                <v-card-text>

                    <v-row v-for="(array_formation,k) in formation.formation" :key="k">
                        <v-col cols="4">
                            <v-input :label="array_formation.split('/')[0].trim()" />
                        </v-col>
                        <v-col cols="3">
                            <v-icon>mdi-chevron-right</v-icon>
                        </v-col>
                        <v-col cols="4">
                            {{array_formation.split('/')[1].trim()}}
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
                        Formation Scolaire
                    </v-card-title>

                    <v-spacer></v-spacer>

                    <v-card-text>

                        <v-row v-for="(array_formation,k) in data.temp_formation" :key="k">
                            <v-col cols="4">
                                <v-text-field v-model="data.temp_formation[k][0]" :rules="[rules.required]" label="Titre du diplôme" dense />
                            </v-col>
                            <v-col cols="1">
                                <v-icon>mdi-chevron-right</v-icon>
                            </v-col>
                            <v-col cols="4">
                                <v-text-field v-model="data.temp_formation[k][1]" :rules="[rules.required]" label="Année d'obtention" dense></v-text-field>
                            </v-col>
                            <v-col cols="1">
                                <v-btn color="primary" depressed @click="deleteRow(k,data.temp_formation[k])">
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
    MICRO_FORMATIONS
} from "@/constant/api";

export default {
    middleware: 'auth',
    async asyncData(ctx) {
        let formation = {}
        try {
            formation = await ctx.$axios.get(MICRO_FORMATIONS.concat('/cv/formations'), {
                params: {
                    id: ctx.store.getters["session/getId"]
                }
            })
        } catch (e) {
            alert(e.message)
        }
        console.log(formation)
        console.log(formation.data)
        return {
            formation: formation.data
        }
    },
    data: () => ({
        show: false,
        isEdit: false,
        data: {
            temp_formation: []
        },
        rules: {
            required: (value) => !!value || 'Ce champ est requis',
        },
    }),

    methods: {
        preModification() {
            this.data.temp_formation = []
            if(this.formation.formation!=null)
                this.formation.formation.forEach(formation => {
                    this.data.temp_formation.push([formation.split('/')[0].trim(), formation.split('/')[1].trim()])
                });
            this.isEdit = true
        },
        addElem() {
            this.data.temp_formation.push(["", ""])
        },
        deleteRow(index, formation) {
            this.data.temp_formation.splice(index, 1)
        },
        async modifier() {
            if (this.$refs.form.validate()) {
                const tmp = {
                }
                tmp.formation=[]
                tmp.id = this.$store.getters['session/getId']
                console.log(tmp)
                this.data.temp_formation.forEach(element => {
                    tmp.formation.push(element[0].concat('/',element[1]))
                });
                console.log(tmp)
                this.$nuxt.$emit('overlay', true)
                try {
                    await this.$axios.post(MICRO_FORMATIONS.concat('/cv/modifier'), {
                        ...tmp
                    })
                    this.formation = tmp
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
