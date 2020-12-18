import Vue from 'vue'
import Vuetify from 'vuetify'
import 'vuetify/dist/vuetify.min.css'
import colors from 'vuetify/lib/util/colors'

Vue.use(Vuetify)

export default new Vuetify({
  theme: {
    themes: {
      light: {
        primary: colors.green.darken1,
        secondary: colors.green.lighten2,
        accent: colors.green.lighten3,
        anchor: colors.green.darken2
      },
      dark: {
        primary: colors.green.darken3,
        secondary: colors.green.darken1,
        normal: colors.green.darken3
      }
    }
  }
})

