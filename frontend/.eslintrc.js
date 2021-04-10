module.exports = {
  root: true,
  parserOptions: {
      parser: 'babel-eslint',
      sourceType: 'module',
      ecmaVersion: 2018,
  },
  env: {
      browser: true,
      node: true
  },
  extends: [
      "eslint:recommended",
      "plugin:vue/recommended"
  ],
  // required to lint *.vue files
  plugins: [
      'vue'
  ],
  globals: {
      'ga': true, // Google Analytics
      'cordova': true,
      '__statics': true
  },
  rules: {
      "strict": [
          "error"
      ],
      "indent": [
          "warn",
          2
      ],
      "quotes": [
          "warn",
          "double"
      ],
      "semi": [
          "error",
          "always"
      ],
      "no-constant-condition": [
          "off"
      ],
      "brace-style": [
          "warn",
          "1tbs",
          {
              "allowSingleLine": true
          }
      ],
      "no-console": [
          "off"
      ],
      "no-unused-vars": "warn",
      "vue/no-use-v-if-with-v-for": ["warn", {
          "allowUsingIterationVar": true
      }],
      "vue/html-self-closing": "off",
      "vue/max-attributes-per-line": "off",
      "vue/require-prop-types": "off",
      "vue/require-default-prop": "off",
      "vue/mustache-interpolation-spacing": "off",
      "vue/no-unused-vars": "warn",
      "vue/no-unused-components": "warn",
      "vue/no-v-html": "off",


      'no-debugger': process.env.NODE_ENV === 'production' ? 2 : 0
  }
}