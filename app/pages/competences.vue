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

                    <v-row>
                    <v-col cols="2">
                        <v-input label="Compétence 1" />
                    </v-col>
                    <v-col cols="3">
                        <v-icon>mdi-chevron-right</v-icon>
                    </v-col>
                    <v-col cols="4">
                        niveau expert
                    </v-col>
                    </v-row>

                    <v-row>
                    <v-col cols="2">
                        <v-input label="Compétence 2" />
                    </v-col>
                    <v-col cols="3">
                        <v-icon>mdi-chevron-right</v-icon>
                    </v-col>
                    <v-col cols="4">
                        niveau intermediaire
                    </v-col>
                    </v-row>

                </v-card-text>

                <v-card-actions>
                    <v-spacer />
                    <v-btn color="success" @click.stop="preModification">
                    <v-icon left>mdi-pencil</v-icon>
                    Modifier
                    </v-btn>
                </v-card-actions>
            </v-card>

            <v-card v-if=isEdit class="mx-auto" max-width="1000">
                <v-card-title>
                    Mes Compétences
                </v-card-title>

                <v-spacer></v-spacer>

                 <v-card-text>

                    <v-row>
                        <v-col cols="2">
                            <v-text-field
                                class="pt-0 mt-0"
                                label="Compétence1"
                            />
                        </v-col>
                        <v-col cols="3">
                            <v-icon>mdi-chevron-right</v-icon>
                        </v-col>
                        <v-col cols="4">
                        <v-text-field
                            class="pt-0 mt-0"
                            label="niveau"
                        />
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="2">
                            <v-text-field
                                class="pt-0 mt-0"
                                label="Compétence2"
                            />
                        </v-col>
                        <v-col cols="3">
                            <v-icon>mdi-chevron-right</v-icon>
                        </v-col>
                        <v-col cols="4">
                        <v-text-field
                            class="pt-0 mt-0"
                            label="niveau"
                        />
                        </v-col>
                    </v-row>

                </v-card-text>

                <v-card-actions>
                    <v-btn
                    class="mx-2"
                    fab
                    dark
                    color="indigo"
                    @click.stop="addElem"
                    >
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
            </v-card>
        </v-col>
    </v-row>
</v-container>
</template>

<script>
import {
    MICRO_EXPERIENCE
} from "@/constant/api";

export default {
    middleware: 'auth',
    async asyncData(ctx) {
        let experience = {}
        try {
            experience = await ctx.$axios.get(MICRO_EXPERIENCE.concat('/cv/experience'), {
                params: {
                    id: ctx.store.getters["session/getId"]
                }
            })
        } catch (e) {
            alert(e.message)
        }
        console.log(experience)
        console.log(experience.data)
        return {
            experience: experience.data
        }
    },
    data: () => ({
        show: false,
        isEdit: false,
        data: {

        },
    }),
    methods: {
        preModification() {
            this.isEdit = true
        },
        async modifier() {

        },
        async addElem(){

        },

    }
}
</script>
