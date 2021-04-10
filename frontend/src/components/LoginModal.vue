<template>
  <q-dialog ref="dialog" @hide="onDialogHide">
    <q-card class="q-dialog-plugin">
      <q-card-section>
        <div class="text-h6">
          Login
        </div>
      </q-card-section>
      <q-card-section>
        <q-input v-model="credentials.email" label="Email" :error="$v.credentials.email.$error">
          <template #prepend>
            <q-icon name="mail" />
          </template>
        </q-input>
        <q-input v-model="credentials.password" icon="password" type="password" label="Password" :error="$v.credentials.password.$error">
          <template #prepend>
            <q-icon name="lock" />
          </template>
        </q-input>
        <p v-if="error" class="text-negative">
          Username o password non valide
        </p>
      </q-card-section>

      <q-card-actions align="right">
        <q-btn color="primary" icon="login" label="Login" :disable="$v.credentials.$invalid" @click="onOKClick" />
        <q-btn color="primary" label="Cancel" flat @click="onCancelClick" />
      </q-card-actions>
    </q-card>
  </q-dialog>
</template>

<script>
import { required } from "vuelidate/lib/validators";
import AuthService from "src/services/AuthService";

export default {
  data(){
    return{
      credentials:{
        email:"luana.verdi@cupcakefactory.it",
        password: "cupcake"
      },
      error: false
    };
  },
  validations:{
    credentials: {
      email: {required},
      password: {required}
    }
  },
  methods: {
    show () {
      this.$refs.dialog.show();
    },
    hide () {
      this.$refs.dialog.hide();
    },
    onDialogHide () {
      this.$emit("hide");
    },
    async onOKClick () {
      try{
        let user = await AuthService.login(this.credentials);
        this.$emit("ok", user);
        this.hide();
      }catch(err){
        console.log(err);
        this.error = true;
        this.credentials.password = "";
      }    
    },
    onCancelClick () {
      this.hide();
    }
  }
};
</script>