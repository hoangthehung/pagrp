<template>
    <div class="container">
        <div class="large-12 medium-12 small-12 cell">
            <label>Select date
                <input type="date" v-model="selectDate" id="selectDate"/>
            </label>
            <button v-on:click="search()">Search</button>
            <div v-if="shops.length > 0">
                <h4 class="text-left" v-if="selectDate != null">Shops are actived on <span>{{selectDate}}</span></h4>
                <h4 class="text-left" v-if="selectDate == null">Search in all shops <span>{{selectDate}}</span></h4>
            <v-table :data="shops" class="table">
            <thead slot="head">
            <th>Id</th>
            <th>Start Date</th>
            <th>End Date</th>
            </thead>
            <tbody slot="body" slot-scope="{displayData}">
            <tr v-for="row in displayData" :key="row.id">
                <td>{{ row.id }}</td>
                <td>{{ row.startDate }}</td>
                <td>{{ row.endDate }}</td>
            </tr>
            </tbody>
            </v-table>
            </div>
        </div>
    </div>

</template>

<script>
    // import {search, upload} from '@/api/shop'
    import axios from 'axios'

    const API_URL = 'http://localhost:8088';
    function formatDate(date) {
        var d = new Date(date),
            month = '' + (d.getMonth() + 1),
            day = '' + d.getDate(),
            year = d.getFullYear();

        if (month.length < 2) month = '0' + month;
        if (day.length < 2) day = '0' + day;

        return [year, month, day].join('');
    }
    export default {
        name: 'upload',
        data() {
            return {
                response: [],
                errors: [],
                file: null,
                selectDate: null,
                shops: [],
            }
        },
        methods: {
            search() {
                console.log(this.selectDate);
                var selDate = '';
                if (this.selectDate != null)
                    selDate = formatDate(this.selectDate);
                var vm = this;
                axios.get(`${API_URL}/shop/search`, {
                    params: {
                        date: selDate
                    }
                }).then(function (data) {
                    console.log(data);
                    vm.shops = data.data.data;
                })
            }
        }
    }

</script>


<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
