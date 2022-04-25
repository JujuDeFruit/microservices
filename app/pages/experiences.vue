<template>
<v-container>
    <v-row justify="right" align="right">
        <v-col cols="12">

            <v-card v-if=!isEdit class="mx-auto" max-width="500">
                <v-card-title>
                    {{experience.titre}}
                </v-card-title>

                <v-spacer></v-spacer>

                <v-card-subtitle>
                    <v-row>
                        <v-col md="auto">
                            <v-icon>mdi-calendar-blank</v-icon>
                        </v-col>
                        <v-col md="auto">
                            <v-text-field v-model="experience.date1" readonly label="De" width="20" />
                        </v-col>
                        <v-col md="auto">
                            <v-text-field v-model="experience.date2" readonly label="À" />
                        </v-col>
                    </v-row>
                </v-card-subtitle>

                <v-card-subtitle>
                    <v-row>
                        <v-col md="auto">
                            <v-icon>mdi-map-marker</v-icon>
                        </v-col>
                        <v-col md="auto">
                            {{experience.lieu}}
                        </v-col>
                    </v-row>
                </v-card-subtitle>

                <v-card-actions>
                    <v-btn color="error" rounded @click.stop="preModification">
                        <v-icon left>mdi-pencil</v-icon>
                        Modifier
                    </v-btn>

                    <v-spacer></v-spacer>

                    <v-btn text @click="show = !show">
                        Détail
                        <v-icon left>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                    </v-btn>
                </v-card-actions>

                <v-expand-transition>
                    <div v-show="show">
                        <v-divider></v-divider>

                        <v-card-text>
                            {{experience.detail}}
                        </v-card-text>
                    </div>
                </v-expand-transition>
            </v-card>

            <v-card v-if=isEdit class="mx-auto" max-width="500">
                <v-card-title>
                    <v-row>
                        <v-col md="auto">
                            <v-text-field v-model="data.titre" />
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
                            <v-text-field v-model="data.date1" label="De" />
                        </v-col>
                        <v-col md="auto">
                            <v-text-field v-model="data.date2" label="À" />
                        </v-col>
                    </v-row>
                </v-card-subtitle>

                <v-card-subtitle>
                    <v-row>
                        <v-col md="auto">
                            <v-icon>mdi-map-marker</v-icon>
                        </v-col>
                        <v-col md="auto">
                            <v-text-field v-model="data.lieu" dense />
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

                    <v-btn text @click="show = !show">
                        Détail
                        <v-icon left>{{ show ? 'mdi-chevron-up' : 'mdi-chevron-down' }}</v-icon>
                    </v-btn>
                </v-card-actions>

                <v-expand-transition>
                    <div v-show="show">
                        <v-divider></v-divider>

                        <v-card-text>
                            <v-textarea v-model="data.detail" dense />
                        </v-card-text>
                    </div>
                </v-expand-transition>
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
            date1: "",
            date2: "",
            lieu: "",
            titre: "",
            detail: ""
        },
    }),
    methods: {
        preModification() {
            this.data.date1 = this.experience.date1 ?? ""
            this.data.date2 = this.experience.date2 ?? ""
            this.data.lieu = this.experience.lieu ?? ""
            this.data.titre = this.experience.titre ?? ""
            this.data.detail = this.experience.detail ?? ""
            this.isEdit = true
        },
        async modifier() {
            const tmp = {
                ...this.data
            }
            tmp.id = this.$store.getters['session/getId']
            this.$nuxt.$emit('overlay', true)
            console.log(tmp)
            try {
                await this.$axios.post(MICRO_EXPERIENCE.concat('/experience/modifier'), {
                    ...tmp
                })
                this.experience = tmp
            } catch (e) {
                alert(e)
            } finally {
                this.$nuxt.$emit('overlay', false)
                this.isEdit = false
            }
        },

    }
}
</script>
