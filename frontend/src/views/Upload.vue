<template>
    <div class="container">
        <div class="large-12 medium-12 small-12 cell">
            <label>Select file to import
                <input type="file" id="file" ref="file" v-on:change="handleFileUpload()"/>
            </label>
            <button v-on:click="submitFile()">Submit</button>
            <div v-if="duplicateShops.length > 0">
                <h4 class="text-left">Shops is duplicated</h4>
            <v-table :data="duplicateShops" class="table">
            <thead slot="head">
            <th>Id</th>
            <th>Start Date</th>
            <th>End Date</th>
            </thead>
            <tbody slot="body" slot-scope="{displayData}">
            <tr v-for="row in displayData" :key="row.shop.id">
                <td>{{ row.shop.id }}</td>
                <td>{{ row.shop.startDate }}</td>
                <td>{{ row.shop.endDate }}</td>
            </tr>
            </tbody>
            </v-table>
            </div>
            <div v-if="invalidShops.length > 0">
                <h4 class="text-left">Shops have invalid date time</h4>
            <v-table :data="invalidShops" class="table">
                <thead slot="head">
                <th>Id</th>
                <th>Start Date</th>
                <th>End Date</th>
                </thead>
                <tbody slot="body" slot-scope="{displayData}">
                <tr v-for="row in displayData" :key="row.shop.id">
                    <td>{{ row.shop.id }}</td>
                    <td>{{ row.shop.startDate }}</td>
                    <td>{{ row.shop.endDate }}</td>
                </tr>
                </tbody>
            </v-table>
            </div>
            <div v-if="isImportSuccess">
                <h4 class="text-left">All items is valid and imported</h4>
            </div>
        </div>
    </div>

</template>

<script>
    // import {search, upload} from '@/api/shop'
    import axios from 'axios'

    const API_URL = 'http://localhost:8088';
    export default {
        name: 'upload',
        data() {
            return {
                response: [],
                errors: [],
                file: null,
                selectDate: null,
                duplicateShops: [],
                invalidShops: [],
                isImportSuccess: false
            }
        },
        methods: {
            submitFile() {
                let formData = new FormData();
                formData.append('file', this.file);
                var vm = this;
                vm.duplicateShops = [];
                vm.invalidShops = [];
                vm.isImportSuccess = false;
                axios.post(`${API_URL}/shop/upload`, formData, {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }).then(function (rs) {
                    console.log('SUCCESS!!', rs.data);
                    vm.duplicateShops = rs.data.duplicatedShops;
                    vm.invalidShops = rs.data.invalidDateShops;
                    vm.isImportSuccess = rs.data.importSuccess;
                    console.log('invalid:', vm.invalidShops)
                })
                    .catch(function () {
                        console.log('FAILURE!!');
                    });
            },
            search() {
                axios.get(`${API_URL}/shop/search`, {'date': this.selectDate}).then(function (data) {
                    console.log(data);
                })
            },
            handleFileUpload() {
                this.file = this.$refs.file.files[0];
            }
        }
    }

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
